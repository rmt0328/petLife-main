/*
package com.yxy.pet.common.basic.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import java.security.Key;

import java.util.Date;
import java.util.Map;

@Component  //将一个类表示为Spring管理的组件
//通过@component注解，spring容器会自动扫描并加载被注解的类，并将其实例化为一个Spring bean
//这样，我们就可以在应用程序的其他部分使用@Autowired、@Resource等注解来注入该组件，并通过spring的依赖注入功能进行管理
public class JWTUtils {
    public static String createJWT(String secretKey, long ttlMillis, Map<String,Object> claims){
        //设置JWT的过期时间
        long nowMillis=System.currentTimeMillis();
        Date now =new Date(nowMillis);
        long expMillis=nowMillis+ttlMillis;
        Date exp=new Date(expMillis);

        // 使用 HMAC256 签名算法生成密钥
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // 构建 JWT
        return Jwts.builder()
                .setClaims(claims) // 设置 payload
                .setIssuedAt(now) // 设置签发时间
                .setExpiration(exp) // 设置过期时间
                .signWith(key, SignatureAlgorithm.HS256) // 使用 HMAC256 签名算法进行签名
                .compact(); // 生成 JWT 字符串

    }


    //验证token
    public static boolean validateJWT(String jwt,String secretKey){
        try {
            // 使用 HMAC256 签名算法生成密钥
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

            // 解析 JWT 并验证签名
            Jwts.parserBuilder()
                    .setSigningKey(key) // 设置签名密钥
                    .build()
                    .parseClaimsJws(jwt); // 解析 JWT

            // 如果没有抛出异常，则表示验证通过
            return true;
        } catch (Exception e) {
            // 如果解析或验证签名失败，则返回 false
            return false;
        }
    }
}
*/
