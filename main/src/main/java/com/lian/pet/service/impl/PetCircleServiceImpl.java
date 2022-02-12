package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lian.pet.common.oss.aliyun.service.config.properties.AliYunOssProperties;
import com.lian.pet.domain.dto.AddPetCircleDTO;
import com.lian.pet.domain.dto.QueryPetCircleDTO;
import com.lian.pet.domain.entity.PetCircle;
import com.lian.pet.domain.entity.WxUser;
import com.lian.pet.domain.vo.PetCircleVO;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.PetCircleMapper;
import com.lian.pet.mapper.WxUserMapper;
import com.lian.pet.service.PetCircleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: Lian
 * @Time: 2022/1/28 22:20
 */
@Slf4j
@Service
@AllArgsConstructor
public class PetCircleServiceImpl implements PetCircleService {
    private final PetCircleMapper petCircleMapper;
    private final WxUserMapper wxUserMapper;
    private final AliYunOssProperties ossProperties;

    @Override
    public void addPetCircle(AddPetCircleDTO req) {
        PetCircle petCircle = ResponseBeanFactory.getPetCircle(req, ossProperties.getUrlPrefix());
        petCircleMapper.insert(petCircle);
        log.info("执行成功[发表宠物圈]");
    }

    @Override
    public List<PetCircleVO> queryPetCircles(QueryPetCircleDTO req) {
        IPage<PetCircle> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<PetCircle> iPage = petCircleMapper.selectPage(page, Wrappers.<PetCircle>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getOpenId()), PetCircle::getUserId, req.getOpenId())
                .orderByDesc(PetCircle::getUpdateTime));
        List<PetCircle> petCircles = iPage.getRecords();
        if (petCircles.isEmpty()) {
            return null;
        }
        List<PetCircleVO> petCircleVOS = new ArrayList<>();
        petCircles.forEach(petCircle -> {
            WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, petCircle.getUserId()));
            PetCircleVO petCircleVO = PetCircleVO.fromPetCircle(petCircle, wxUser.getNickName(), wxUser.getAvatarUrl(), ossProperties.getUrlPrefix());
            petCircleVOS.add(petCircleVO);
        });
        log.info("执行成功[查询宠物圈列表]");
        return petCircleVOS;
    }

    @Override
    public PetCircleVO getPetCircle(String id) {
        PetCircle petCircle = petCircleMapper.selectById(id);
        WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, petCircle.getUserId()));
        return PetCircleVO.fromPetCircle(petCircle, wxUser.getNickName(), wxUser.getAvatarUrl(), ossProperties.getUrlPrefix());
    }
}
