package com.taskmanager.demotaskmanager.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String JWT_SECRET = "wJtSuPeRSeCrEtK3YWithLongRandomSafeChars_!#%$^&*123456";

    private Key getSigningKey() {
        byte[] keyBytes = JWT_SECRET.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUsername(String token) {
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

    public boolean isTokenValid(String jwt, com.taskmanager.demotaskmanager.model.User user) {
        final String username = extractUsername(jwt);
        return (username.equals(user.getEmail()) && !isTokenExpired(jwt));
    }

    public String generateToken(com.taskmanager.demotaskmanager.model.User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId",user.getId())
                .claim("role",user.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
                .compact();
    }
}
