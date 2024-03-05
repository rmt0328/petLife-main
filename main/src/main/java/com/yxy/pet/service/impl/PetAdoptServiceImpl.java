package com.yxy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxy.pet.common.basic.enums.FinishStatusEnum;
import com.yxy.pet.common.basic.enums.IsPassEnum;
import com.yxy.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.yxy.pet.domain.dto.AddPetAdoptDTO;
import com.yxy.pet.domain.dto.QueryAdoptDTO;
import com.yxy.pet.domain.dto.QueryAdoptsInDTO;
import com.yxy.pet.domain.entity.AdoptApply;
import com.yxy.pet.domain.entity.PetAdopt;
import com.yxy.pet.domain.entity.WxUser;
import com.yxy.pet.domain.vo.AdoptAndUserVO;
import com.yxy.pet.domain.vo.PetAdoptVO;
import com.yxy.pet.domain.vo.PetCountVO;
import com.yxy.pet.domain.vo.WxUserVO;
import com.yxy.pet.factory.ResponseBeanFactory;
import com.yxy.pet.mapper.AdoptApplyMapper;
import com.yxy.pet.mapper.PetAdoptMapper;
import com.yxy.pet.mapper.WxUserMapper;
import com.yxy.pet.service.PetAdoptService;
import com.yxy.pet.service.PetFindService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2023/5/18 19:02
 */
@Slf4j
@AllArgsConstructor
@Service
public class PetAdoptServiceImpl implements PetAdoptService {
    private final PetAdoptMapper petAdoptMapper;
    private final AliYunOssProperties ossProperties;
    private final WxUserMapper wxUserMapper;
    private final PetFindService petFindService;
    private final AdoptApplyMapper adoptApplyMapper;

    @Override
    public void addPetAdopt(AddPetAdoptDTO req) {
        PetAdopt petAdopt = ResponseBeanFactory.getPetAdopt(req, ossProperties.getUrlPrefix());
        petAdoptMapper.insert(petAdopt);
        log.info("执行成功[发布宠物领养]");
    }

    @Override
    public List<PetAdoptVO> queryAdoptList(QueryAdoptDTO req) {
        IPage<PetAdopt> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<PetAdopt> iPage = petAdoptMapper.selectPage(page, Wrappers.<PetAdopt>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getOpenId()), PetAdopt::getOpenId, req.getOpenId())
                .eq(StringUtils.isNotBlank(req.getCity()), PetAdopt::getCity, req.getCity())
                .orderByDesc(PetAdopt::getIsFinish)
                .orderByDesc(PetAdopt::getCreateTime));
        List<PetAdoptVO> petAdoptVOS = iPage.getRecords().stream()
                .map(PetAdoptVO::fromPetAdopt).collect(Collectors.toList());
        log.info("执行成功[查询领养列表]");
        return petAdoptVOS;
    }

    @Override
    public AdoptAndUserVO getPetAdoptById(Integer adoptId, Integer applyId, String userId) {
        PetAdopt petAdopt = petAdoptMapper.selectById(adoptId);
        PetAdopt adopt = new PetAdopt();
        adopt.setId(adoptId);
        // 浏览量+1
        adopt.setView(petAdopt.getView() + 1);
        petAdoptMapper.updateById(adopt);
        // 送养人信息
        WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, petAdopt.getOpenId()));
        // 正在送养数量
        Integer finishingCount = petAdoptMapper.selectCount(Wrappers.<PetAdopt>lambdaQuery()
                .eq(PetAdopt::getOpenId, wxUser.getOpenId())
                .eq(PetAdopt::getIsFinish, FinishStatusEnum.FINISHING.code()));
        // 已送养数量
        Integer finishedCount = petAdoptMapper.selectCount(Wrappers.<PetAdopt>lambdaQuery()
                .eq(PetAdopt::getOpenId, wxUser.getOpenId())
                .eq(PetAdopt::getIsFinish, FinishStatusEnum.FINISHED.code()));

        AdoptAndUserVO resultVO = AdoptAndUserVO.builder()
                .petAdoptVO(PetAdoptVO.fromPetAdopt(petAdopt))
                .wxUserVO(WxUserVO.fromWxUser(wxUser))
                .build();

        // 申请状态
        AdoptApply adoptApply;
        if (applyId != null) {
            adoptApply = adoptApplyMapper.selectOne(Wrappers.<AdoptApply>lambdaQuery().eq(AdoptApply::getId, applyId));
        } else {
            List<AdoptApply> adoptApplies = adoptApplyMapper.selectList(Wrappers.<AdoptApply>lambdaQuery()
                    .eq(AdoptApply::getAdoptId, adoptId)
                    .eq(AdoptApply::getUserId, userId)
                    .orderByDesc(AdoptApply::getCreateTime)
                    .last("limit 1"));
            adoptApply = CollectionUtils.isEmpty(adoptApplies) ? null : adoptApplies.get(0);
        }
        if (ObjectUtils.isEmpty(adoptApply)) {
            resultVO.getPetAdoptVO().setIsApply("3");
        }else {
            if (adoptApply.getIsPassed().equals(IsPassEnum.NO_PASS.code())) {
                resultVO.getPetAdoptVO().setIsApply("0");
            } else if (adoptApply.getIsPassed().equals(IsPassEnum.PASSED.code())) {
                resultVO.getPetAdoptVO().setIsApply("1");
            } else if (adoptApply.getIsPassed().equals(IsPassEnum.IS_AUDIT.code())) {
                resultVO.getPetAdoptVO().setIsApply("2");
            }
        }

        resultVO.getWxUserVO().setDoingNum(finishingCount);
        resultVO.getWxUserVO().setDoneNum(finishedCount);
        log.info("执行成功[查询领养详情]adoptId={}", adoptId);
        return resultVO;
    }

    @Override
    public PetCountVO queryCount(String openId) {
        Integer petAdoptCount = petAdoptMapper.selectCount(Wrappers.<PetAdopt>lambdaQuery().eq(PetAdopt::getOpenId, openId));
        Integer petFindCount = petFindService.queryCount(openId);
        log.info("执行成功[查询领养/寻宠总数],openId={}", openId);
        return PetCountVO.builder()
                .petAdoptCount(petAdoptCount)
                .petFindCount(petFindCount)
                .build();
    }

    @Override
    public List<PetAdoptVO> queryAdoptsInIds(QueryAdoptsInDTO req) {
        if (req.getIds().isEmpty()) {
            return Collections.emptyList();
        }
        Page<PetAdopt> page = new Page<>(req.getPageNum(), req.getPageSize());
        Page<PetAdopt> iPage = petAdoptMapper.selectPage(page, Wrappers.<PetAdopt>lambdaQuery()
                .in(PetAdopt::getId, req.getIds()));
        List<PetAdopt> petAdopts = iPage.getRecords();
        if (petAdopts.isEmpty()) {
            return Collections.emptyList();
        }
        List<PetAdoptVO> petAdoptVOS = petAdopts.stream()
                .map(PetAdoptVO::fromPetAdopt).collect(Collectors.toList());
        log.info("执行成功[查询领养列表]");
        return petAdoptVOS;
    }
}
