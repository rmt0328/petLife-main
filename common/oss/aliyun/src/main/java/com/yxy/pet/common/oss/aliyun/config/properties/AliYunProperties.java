package com.yxy.pet.common.oss.aliyun.config.properties;

import com.aliyun.oss.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @Desc: 阿里云配置
 * @Author: yxy
 * @Time: 2022/1/26 10:31
 */
@Data
@ConfigurationProperties(prefix = "aliyun")
@PropertySource(value = {"classpath:oss-conf.properties"}, encoding = "UTF-8")
public class AliYunProperties {
    /**
     * @Autowired
     * 可指定 aliYunOssProperties 优先加载
     */
    @Autowired
    AliYunOssProperties aliYunOssProperties;
    private String accessKeyId;
    private String accessKeySecret;

    /**
     * 新版
     * @return
     */
    @Bean
    public OSS ossClient() {
        // 设置超时机制和重试机制
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setConnectionTimeout(5000);
        conf.setMaxErrorRetry(3);
        return new OSSClientBuilder().build(aliYunOssProperties.getEndpoint(), accessKeyId, accessKeySecret, conf);
    }
}
