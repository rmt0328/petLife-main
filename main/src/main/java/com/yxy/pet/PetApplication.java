package com.yxy.pet;

import com.yxy.pet.config.properties.WechatAppletProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("com.yxy.pet.mapper")
@EnableConfigurationProperties({WechatAppletProperties.class})
@SpringBootApplication
public class PetApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetApplication.class, args);
    }

}
