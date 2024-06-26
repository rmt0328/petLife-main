package com.yxy.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.entity.ResnetResult;
import com.yxy.pet.mapper.ResnetResultMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2024/3/9 0:46
 */
@Slf4j
@AllArgsConstructor
@Service
public class PredictService {
    ResnetResultMapper resnetResultMapper;


    public AppResp<List<ResnetResult>> getByPredictId(String predictId) {
        QueryWrapper<ResnetResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("predict_id", predictId);
        List<ResnetResult> resnetResults = resnetResultMapper.selectList(queryWrapper);
        return AppResp.succeed(resnetResults);
    }
}
