package com.yxy.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxy.pet.common.basic.enums.DataTypeEnum;
import com.yxy.pet.common.cache.redis.service.RedisService;
import com.yxy.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.yxy.pet.domain.dto.AddPetCircleDTO;
import com.yxy.pet.domain.dto.QueryPetCircleDTO;
import com.yxy.pet.domain.entity.PetCircle;
import com.yxy.pet.domain.entity.WxUser;
import com.yxy.pet.domain.vo.PetCircleVO;
import com.yxy.pet.factory.ResponseBeanFactory;
import com.yxy.pet.mapper.PetCircleMapper;
import com.yxy.pet.mapper.WxUserMapper;
import com.yxy.pet.service.PetCircleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/28 22:20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PetCircleServiceImpl implements PetCircleService {
    private final PetCircleMapper petCircleMapper;
    private final WxUserMapper wxUserMapper;
    private final AliYunOssProperties ossProperties;
    private final RedisService redisService;

    @Override
    public void addPetCircle(AddPetCircleDTO req) {
        PetCircle petCircle = ResponseBeanFactory.getPetCircle(req, ossProperties.getUrlPrefix());
        petCircleMapper.insert(petCircle);
        // 添加数据时 删除Cache数据
        redisService.delKey(DataTypeEnum.CIRCLE.name());
        log.info("执行成功[发表宠物圈]");
    }

    @Override
    public List<PetCircleVO> queryPetCircles(QueryPetCircleDTO req) {
//        String cacheField = req.getPageNum().toString().concat(StringUtils.isNotBlank(req.getOpenId()) ? req.getOpenId() : "");
//        Object obj = redisService.hGet(DataTypeEnum.CIRCLE.name(), cacheField);
//        if (!ObjectUtils.isEmpty(obj)) {
//            CacheList cacheList = JSONObject.parseObject(obj.toString(), CacheList.class);
//            log.info("取出缓存中数据");
//            return cacheList.getPetCircleVOS();
//        }
        IPage<PetCircle> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<PetCircle> iPage = petCircleMapper.selectPage(page, Wrappers.<PetCircle>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getOpenId()), PetCircle::getUserId, req.getOpenId())
                .orderByDesc(PetCircle::getUpdateTime));
        List<PetCircle> petCircles = iPage.getRecords();
        if (petCircles.isEmpty()) {
            return Collections.emptyList();
        }
        List<PetCircleVO> petCircleVOS = new ArrayList<>();
        petCircles.forEach(petCircle -> {
            WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenId, petCircle.getUserId()));
            PetCircleVO petCircleVO = PetCircleVO.fromPetCircle(petCircle, wxUser.getNickName(), wxUser.getAvatarUrl(), ossProperties.getUrlPrefix());
            petCircleVOS.add(petCircleVO);
        });
        // 缓存列表数据
//        redisService.hPut(DataTypeEnum.CIRCLE.name(), cacheField,
//                JSONObject.toJSONString(CacheList.builder()
//                .petCircleVOS(petCircleVOS)
//                .build()));
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
