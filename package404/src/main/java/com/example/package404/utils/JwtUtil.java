package com.example.package404.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtUtil {
    //yml 파일에 설정
    @Value("${jwt.secret}")
    private String secret;

    private static String SECRET;

    @Value("${jwt.expiration}")
    private int expirationTime;

    private static int EXP;

    @PostConstruct
    public void init() {
        SECRET = secret;
        EXP = expirationTime;
    }

    public static String generateToken(Long userIdx, String userEmail, Collection<? extends GrantedAuthority> userRole) {
        Claims claims = Jwts.claims();
        claims.put("userRole", userRole);
        claims.put("userEmail", userEmail);
        claims.put("userIdx", userIdx);
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public static String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
