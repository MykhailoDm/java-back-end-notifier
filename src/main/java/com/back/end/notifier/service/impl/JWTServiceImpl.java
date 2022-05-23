package com.back.end.notifier.service.impl;

import com.back.end.notifier.service.JWTService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JWTServiceImpl implements JWTService {

    @Value("${app.jwt.secret}")
    private String secret;

    // Expiration time in milliseconds
    @Value("${app.jwt.expiration}")
    private int expirationTime;

    @Override
    public String generateJWTToken(String username) {

        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getPropertyFromToken(token, Claims::getSubject);
    }

    private <T> T getPropertyFromToken(String token, Function<Claims, T> extractor) {
        Claims claims = getTokenClaims(token);
        return extractor.apply(claims);
    }

    private Claims getTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isValidJWTToken(String token) {
        try {
            getTokenClaims(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
