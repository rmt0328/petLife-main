package com.lian.pet.common.oss.aliyun.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teautil.Common;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunProperties;
import com.lian.pet.common.oss.aliyun.service.AliYunSmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.aliyun.teaopenapi.models.*;

/**
 * @Desc: TODO 待测试
 * @Author: Lian
 * @Time: 2022/3/2 17:26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AliYunSmsServiceImpl implements AliYunSmsService {
    private final AliYunProperties aliYunProperties;
    private final AliYunOssProperties aliYunOssProperties;

    public Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(aliYunProperties.getAccessKeyId())
                .setAccessKeySecret(aliYunProperties.getAccessKeySecret());
        // 访问的域名
        config.endpoint = aliYunOssProperties.getEndpoint();
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    @Override
    public Boolean sendSms(String phone) throws Exception {
        SendSmsRequest sendReq = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("连连商城")
                .setTemplateCode("SMS_222860055")
                .setTemplateParam("123456");
        Client client = createClient();
        SendSmsResponse sendResp = client.sendSms(sendReq);
        log.info("smsResp: {}", sendResp);
        String code = sendResp.body.code;
        if (!Common.equalString(code, "OK")) {
            throw new RuntimeException("错误信息: " + sendResp.body.message + "");
        }
        return true;
    }
}
