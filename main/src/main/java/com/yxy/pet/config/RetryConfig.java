package com.yxy.pet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Desc: 开启Retry重试机制
 * @Author: yxy
 * @Time: 2022/3/4 15:24
 */
@EnableRetry
@Configuration
public class RetryConfig {
}
