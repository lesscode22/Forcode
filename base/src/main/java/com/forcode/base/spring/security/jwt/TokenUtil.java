package com.forcode.base.spring.security.jwt;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TokenUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    // 过期时间
    private static final long EXPIRATION = 3600L * 100 * 100;
    // 服务器端密钥
    private static final String SECRET = "cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=";

    public static String create(Long userId) {

        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            // 生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            // 设置私有声明
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", userId);

            return Jwts.builder()
                    .setClaims(claims)
                    .setId(IdUtil.fastSimpleUUID())
                    .setSubject(userId.toString())
                    .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION))
                    .signWith(signingKey, signatureAlgorithm)
                    .compact();
        } catch (Exception e) {
            log.error("===========Jwt签名失败: ", e);
        }
        return null;
    }

    public static boolean isVerify(String token, Long userId) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .build()
                .parseClaimsJws(token).getBody();
        String subject = claims.getSubject();
        return subject.equals(userId.toString());
    }

    public static Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .build()
                .parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) {
        String token = create(1L);
        System.out.println(token);

        String a = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsImp0aSI6IjljMTkyMjRkMDJjMjQ4NGM4NWY2MGI5ZDhjMjdiZDYwIiwic3ViIjoiMSIsImV4cCI6MTY3MDUxNzU5Mn0.LFBTk5txYO9mi62po0-jPecDKwMqs885ikdU0tPiC6M";
//        System.out.println(parse(a));
        System.out.println(isVerify(token, 2L));


        Date start = new Date(Instant.now().toEpochMilli());
        Date end = new Date(Instant.now().toEpochMilli() + EXPIRATION);
        System.out.println(DateUtil.format(start, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.format(end, "yyyy-MM-dd HH:mm:ss"));
    }
}
