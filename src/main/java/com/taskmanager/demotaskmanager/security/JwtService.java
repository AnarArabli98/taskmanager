package com.taskmanager.demotaskmanager.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {



    @Value("${application.jwt.secret}")
    private String secretKey;

    @Value("${application.jwt.expiration}")
    private long expiration;


    private Key getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(com.taskmanager.demotaskmanager.model.User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId",user.getId())
                .claim("role",user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isTokenValid(String jwt, com.taskmanager.demotaskmanager.model.User user) {
        final String email = extractEmail(jwt);
        return email.equals(user.getEmail())  && !isTokenExpired(jwt);
    }


    public String extractEmail(String token) {

        return getClaims(token).getSubject();
    }


    private boolean isTokenExpired(String token) {

        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }




}
