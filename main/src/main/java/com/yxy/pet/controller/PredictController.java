package com.yxy.pet.controller;

import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.entity.ResnetResult;
import com.yxy.pet.service.impl.PredictService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2024/3/9 0:45
 */
@Slf4j
@RequestMapping("predict")
@RestController
@AllArgsConstructor
public class PredictController {
    private PredictService predictService;

    @RequestMapping("getByPredictId")
    public AppResp<ResnetResult> getByPredictId(String predictId){
        return predictService.getByPredictId(predictId);
    }
}
