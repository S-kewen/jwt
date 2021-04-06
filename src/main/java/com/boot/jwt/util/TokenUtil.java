package com.boot.jwt.util;

import cn.hutool.crypto.SecureUtil;
import com.boot.jwt.entity.Token;
import com.boot.jwt.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @PackageName: com.boot.jwt.util
 * @ClassName: TokenUtil
 * @Description: This is TokenUtil class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:20
 */
@Component
public class TokenUtil {
    @Value("${jwt.config.salt}")
    private String salt;
    @Value("${jwt.config.ttl}")
    private long ttl;

    public String createToken(User user) {
        JwtBuilder JwtBuilder = Jwts.builder()
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ttl))
                .claim("role", user.getType() == 2 ? "admin" : "user")
                .claim("cipher", SecureUtil.md5(user.getPassword()))//用於判斷密碼是否與當前一致
                .signWith(SignatureAlgorithm.HS256, salt);
        return JwtBuilder.compact();
    }

    public Token parseToken(String tokenStr) {
        Claims claims = Jwts.parser()
                .setSigningKey(salt)
                .parseClaimsJws(tokenStr)
                .getBody();
        User user = new User();
        user.setId(Long.parseLong(claims.getId()));
        //user = userService.getById(user);此處省略
//        if (user != null && user.getState() == 1) {
//
//        } else {
//            return null;
//        }
        Token token = new Token();
        token.setId(Long.parseLong(claims.getId()));
        token.setUsername(claims.getSubject());
        token.setPassword((String) claims.get("cipher"));
        token.setRole((String) claims.get("role"));
        token.setAdd_time(claims.getIssuedAt());
        token.setExpire_time(claims.getExpiration());
        return token;
    }
}
