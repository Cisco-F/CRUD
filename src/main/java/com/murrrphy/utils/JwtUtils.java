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
        String jwt = Jwts.builder()
                .addClaims(claims)//添加jwt携带的信息
                .signWith(SignatureAlgorithm.HS256, signKey)//签名算法和签名密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire))//设置过期时间
                .compact();//将结果转换为字符串
        return jwt;
    }

    //解析jwt令牌
    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)//签名密钥
                .parseClaimsJws(jwt)
                .getBody();//得到json数据
        return claims;
    }

    //获取payload中装载的信息(level)
    public static Integer getLevel(HttpServletRequest request){
        //获取token
        String jwt = request.getHeader("token");
        //获取payload中的信息
        Claims claims = parseJwt(jwt);
        //获取level
        Integer level = (Integer) claims.get("level");
        return level;
    }
}
