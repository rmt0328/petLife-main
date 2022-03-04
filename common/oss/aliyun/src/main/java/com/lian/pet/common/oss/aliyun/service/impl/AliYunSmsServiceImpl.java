package com.lian.pet.common.oss.aliyun.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunProperties;
import com.lian.pet.common.oss.aliyun.service.AliYunSmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Random;

/**
 * @Desc:
 * @Author: Lian
 * @Time: 2022/3/2 17:26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AliYunSmsServiceImpl implements AliYunSmsService {
    private final AliYunProperties aliYunProperties;

    @Override
    public Boolean sendSms(String phone) throws Exception {
        // 短信API产品名称(无需修改)
        final String product = "Dysmsapi";
        // 短信API产品域名(无需修改)
        final String domain = "dysmsapi.aliyuncs.com";
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                aliYunProperties.getAccessKeyId(),
                aliYunProperties.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest sendSmsRequest = getSendSmsRequest(phone);
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(sendSmsRequest);
        if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
            return true;
        }
        return false;
    }

    /**
     * 获取请求
     * @param phone
     * @return
     */
    private SendSmsRequest getSendSmsRequest(String phone) {
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        // 多个手机号逗号分割 上限1000个
        request.setPhoneNumbers(phone);
        request.setSignName("连连商城");
        request.setTemplateCode("SMS_222860055");
        request.setTemplateParam("{\"code\": \"" + getCode() + "\"}");
        return request;
    }

    /**
     * 生成验证码
     * @return
     */
    public String getCode() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
