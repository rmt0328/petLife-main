package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.common.oss.aliyun.service.AliYunOssService;
import com.lian.pet.common.oss.aliyun.service.config.properties.AliYunOssProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @Desc: File Controller
 * @Author: Lian
 * @Time: 2022/1/26 14:38
 */
@Slf4j
@RequestMapping("file")
@RestController
@AllArgsConstructor
public class FileController {
    private final AliYunOssService OssService;
    private final AliYunOssProperties ossProperties;

    /**
     * 上传图片
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadPic")
    public AppResp<String> uploadPic(HttpServletRequest request){
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile file = req.getFile("file");
        return AppResp.succeed(OssService.uploadObjectOSS(file));
    }

    /**
     * 删除图片 by key
     * @param picture
     * @return
     */
    @GetMapping("/deletePic")
    public AppResp<Boolean> deletePic(@RequestParam("picture") String picture) {
        String replace = picture.replace(ossProperties.getUrlPrefix(), "");
        OssService.deleteFile(ossProperties.getBucketName(), replace.substring(0, 18), replace.substring(18));
        return AppResp.succeed(true);
    }

}
