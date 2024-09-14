package com.demure.demure_auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {
    private final StringRedisTemplate redisTemplate;
    @Override
    public void blacklistToken(String token, long expiration_time) {
        redisTemplate.opsForValue().set(token, "blacklisted", expiration_time, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }
}
