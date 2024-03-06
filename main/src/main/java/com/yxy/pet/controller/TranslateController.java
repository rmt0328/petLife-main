package com.yxy.pet.controller;

import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.dto.WxUserDTO;
import com.yxy.pet.domain.entity.PredictionResult;
import com.yxy.pet.service.impl.TranslateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public AppResp<PredictionResult> predict(MultipartFile file/*, @RequestBody WxUserDTO wxUserDTO*/){
        try {
            WxUserDTO wxUserDTO1 = new WxUserDTO();
            wxUserDTO1.setOpenId("oAMiG6weVjAXf15wvfBBTPmT_gsk");
            return translateService.predict(file, wxUserDTO1);
        } catch (IOException e) {
            return AppResp.failed(-1,"解析失败:"+e.getMessage());
        }
    }
}
