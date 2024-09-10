package com.demure.demure_auth.utility;

import com.demure.demure_auth.entity.*;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User createUser(Role role) {
        return switch (role) {
            case ADMIN -> new Admin();
            case RIDER -> new Rider();
            case GUARD -> new Guard();
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
