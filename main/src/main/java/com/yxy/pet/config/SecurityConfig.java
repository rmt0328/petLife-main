package com.yxy.pet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Desc: Security配置
 * @Author: yxy
 * @Time: 2022/1/11 14:56
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .formLogin() // 表单登录。跳转到security默认的登录表单页
            .loginPage("/index/login")
            // http.httpBasic() //basic登录
            .and()
            .authorizeRequests() // 对请求授权
            .antMatchers(
                    // 放行Swagger start
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/v2/**",
                    "/swagger-resources/**",
                    // 放行Swagger end
                    "/index/**",
                    "/user/**",
                    "/adopt/**",
                    "/file/**",
                    "/petFind/**",
                    "/petCircle/**",
                    "/petCyclopedia/**",
                    "/credits/**",
                    "/comment/**",
                    "/article/**",
                    "/chat/**",
                    "/userFollow/**",
                    "/answer/**",
                    "/collect/**",
                    "/complaint/**",
                    "/translate/**",
                    "/predict/**"
                    )
            .permitAll()
            .anyRequest() // 任何请求
            .authenticated() // 需要身份认证
        ;


        //首页允许所有人可以访问，功能页只对有权限的人才能访问
        /*http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/level1/**")
                .hasRole("vip1");*/
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //这些数据正常应该从数据中读
          auth.inMemoryAuthentication()
                  .withUser("caicai")
                  .password("123456")
                  .roles("vip2","vip3")
                  .and()
                  .withUser("root")
                  .password("123456")
                  .roles("vip1","vip2","vip3");
    }
}
