package com.back.end.notifier.service;

public interface JWTService {
    String generateJWTToken(String username);

    String getUsernameFromToken(String token);

    boolean isValidJWTToken(String token);
}
