package com.l3n.ecommerceapp.ecommmerce_app.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.l3n.ecommerceapp.ecommmerce_app.security.service.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.refreshExpirationMs}")
    private int refreshJwtExpirationMs;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    private final Key jwtSecretKey;

    public JwtUtils(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((principal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(jwtSecretKey)
                .compact();
    }

    public String generateRefresJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((principal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + refreshJwtExpirationMs))
                .signWith(jwtSecretKey)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

}
