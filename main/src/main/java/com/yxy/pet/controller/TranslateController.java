package com.yxy.pet.controller;

import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.dto.WxUserDTO;
import com.yxy.pet.domain.entity.PredictionResult;
import com.yxy.pet.service.impl.TranslateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2024/3/6 10:38
 */
@Slf4j
@RequestMapping("translate")
@RestController
@AllArgsConstructor
public class TranslateController {
    private TranslateService translateService;
    @PostMapping("predictImg")
    public AppResp<PredictionResult> predict(String url,String openId){
        try {
            return translateService.predict(url,openId);
        } catch (IOException e) {
            return AppResp.failed(-1,"解析失败:"+e.getMessage());
        }
    }

    @GetMapping("getList")
    public AppResp getList(String openId){
        return translateService.getList(openId);
    }


    @PostMapping("predictImgByFile")
    public AppResp<PredictionResult> predictByFile(MultipartFile file/*, @RequestBody WxUserDTO wxUserDTO*/){
        try {
            WxUserDTO wxUserDTO1 = new WxUserDTO();
            wxUserDTO1.setOpenId("oAMiG6weVjAXf15wvfBBTPmT_gsk");
            return translateService.predictByFile(file, wxUserDTO1);
        } catch (IOException e) {
            return AppResp.failed(-1,"解析失败:"+e.getMessage());
        }
    }


}
