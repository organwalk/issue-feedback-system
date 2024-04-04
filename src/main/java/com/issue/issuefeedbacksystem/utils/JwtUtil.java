package com.issue.issuefeedbacksystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SECRET_KEY_STRING = "3qFv7Zx8Dy1UwRa4Ed7PpFb7Wk5QcTe2";
    private static final byte[] SECRET_KEY_BYTES = Base64.getEncoder().encode(SECRET_KEY_STRING.getBytes()); // 将字符串编码为字节数组并编码为Base64
    private static final Key SECRET_KEY = new SecretKeySpec(SECRET_KEY_BYTES, SignatureAlgorithm.HS256.getJcaName()); // 创建Key对象
    private static final long EXPIRATION_TIME = 864000000; // 10天过期时间

    public String generateToken(Integer userId, Integer roleId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("roleId", roleId);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
