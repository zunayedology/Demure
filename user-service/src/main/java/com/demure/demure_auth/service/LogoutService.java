package com.demure.demure_auth.service;

public interface LogoutService {
    void blacklistToken(String token, long expiration_time);
    boolean isTokenBlacklisted(String token);
}
