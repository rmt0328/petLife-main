package com.yxy.pet.common.oss.aliyun.config;

import com.yxy.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.yxy.pet.common.oss.aliyun.config.properties.AliYunProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Desc: 激活配置类
 * @Author: yxy
 * @Time: 2022/1/26 14:08
 */
@Configuration
@EnableConfigurationProperties({AliYunOssProperties.class, AliYunProperties.class})
@EnableRetry
public class EnableConfig {
}
