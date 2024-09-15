package com.demure.demure_auth.entity;

public record DTO(
        Long userId,
        String username,
        String email,
        String password,
        Role role
) {
    public DTO {
        if (userId != null && userId <= 0) {
            throw new IllegalArgumentException("User ID cannot be zero or negative");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }
}
