package com.yxy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxy.pet.common.basic.enums.FinishStatusEnum;
import com.yxy.pet.common.basic.utils.DateUtil;
import com.yxy.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.yxy.pet.domain.dto.AddPetFindDTO;
import com.yxy.pet.domain.dto.QueryPetFindDTO;
import com.yxy.pet.domain.entity.PetFind;
import com.yxy.pet.domain.entity.WxUser;
import com.yxy.pet.domain.vo.*;
import com.yxy.pet.factory.ResponseBeanFactory;
import com.yxy.pet.mapper.PetFindMapper;
import com.yxy.pet.mapper.WxUserMapper;
import com.yxy.pet.service.PetFindService;
import com.yxy.pet.domain.vo.PetFindAndUserVO;
import com.yxy.pet.domain.vo.PetFindVO;
import com.yxy.pet.domain.vo.WxUserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/28 13:46
 */
@Slf4j
@AllArgsConstructor
@Service
public class PetFindServiceImpl implements PetFindService {
    private final PetFindMapper petFindMapper;
    private final AliYunOssProperties ossProperties;
    private final WxUserMapper wxUserMapper;

    @Override
    public void addPetFind(AddPetFindDTO req) {
        PetFind petFind = ResponseBeanFactory.getPetFind(req, ossProperties.getUrlPrefix());
        petFindMapper.insert(petFind);
        log.info("执行成功[发布寻宠信息]");
    }

    @Override
    public List<PetFindVO> queryPetFindList(QueryPetFindDTO req) {
        String startTime = "";
        if ("1".equals(req.getUpdateTime())) {
            startTime = DateUtil.getPastDate(7);
        }else if ("2".equals(req.getUpdateTime())) {
            startTime = DateUtil.getPastDate(30);
        }else if ("3".equals(req.getUpdateTime())) {
            startTime = DateUtil.getPastDate(90);
        }
        IPage<PetFind> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<PetFind> iPage = petFindMapper.selectPage(page, Wrappers.<PetFind>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getOpenId()), PetFind::getOpenId, req.getOpenId())
                .eq(StringUtils.isNotBlank(req.getCity()), PetFind::getCity, req.getCity())
                .eq(StringUtils.isNotBlank(req.getType()), PetFind::getType, req.getType())
                .eq(StringUtils.isNotBlank(req.getSex()), PetFind::getSex, req.getSex())
                .ge(StringUtils.isNotBlank(req.getUpdateTime()), PetFind::getUpdateTime, startTime)
                .le(StringUtils.isNotBlank(req.getUpdateTime()), PetFind::getUpdateTime, DateUtil.getCurrentTime())
                .orderByDesc(PetFind::getCreateTime));
        List<PetFindVO> petFindVOS = iPage.getRecords().stream()
                .map(PetFindVO::fromPetFind).collect(Collectors.toList());
        log.info("执行成功[查询寻宠列表]");
        return petFindVOS;
    }

    @Override
    public PetFindAndUserVO getPetFindById(Integer petFindId) {
        PetFind petFind = petFindMapper.selectById(petFindId);
        PetFind find = new PetFind();
        find.setId(petFindId);
        // 浏览量+1
        find.setView(petFind.getView() + 1);
        petFindMapper.updateById(find);
        // 送养人信息
        WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, petFind.getOpenId()));
        // 正在送养数量
        Integer finishingCount = petFindMapper.selectCount(Wrappers.<PetFind>lambdaQuery()
                .eq(PetFind::getOpenId, wxUser.getOpenId())
                .eq(PetFind::getIsFinish, FinishStatusEnum.FINISHING.code()));
        // 已送养数量
        Integer finishedCount = petFindMapper.selectCount(Wrappers.<PetFind>lambdaQuery()
                .eq(PetFind::getOpenId, wxUser.getOpenId())
                .eq(PetFind::getIsFinish, FinishStatusEnum.FINISHED.code()));
        PetFindAndUserVO resultVO = PetFindAndUserVO.builder()
                .petFindVO(PetFindVO.fromPetFind(petFind))
                .wxUserVO(WxUserVO.fromWxUser(wxUser))
                .build();
        resultVO.getWxUserVO().setDoingNum(finishingCount);
        resultVO.getWxUserVO().setDoneNum(finishedCount);
        log.info("执行成功[查询寻宠详情]petFindId={}", petFindId);
        return resultVO;
    }

    @Override
    public Integer queryCount(String openId) {
        return petFindMapper.selectCount(Wrappers.<PetFind>lambdaQuery().eq(PetFind::getOpenId, openId));
    }
}
