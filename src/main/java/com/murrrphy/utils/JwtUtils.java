package com.murrrphy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "murrrphy";//签名密钥
    private static Long expire = 43200000L;//过期时间

    //生成jwt令牌
    public static String generateJwt(Map<String, Object> claims){
        return Jwts.builder()
                .addClaims(claims)//添加jwt携带的信息
                .signWith(SignatureAlgorithm.HS256, signKey)//签名算法和签名密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire))//设置过期时间
                .compact();
    }

    //解析jwt令牌，获得payload中装载的所有信息
    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)//签名密钥
                .parseClaimsJws(jwt)
                .getBody();
    }

    //获取payload中装载的信息
    public static Claims getData(HttpServletRequest request){
        //获取token
        String jwt = request.getHeader("token");
        //获取payload中的信息
        return parseJwt(jwt);
    }
}
