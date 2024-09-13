package com.demure.demure_auth.utility;

import com.demure.demure_auth.entity.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User createUser(@NotNull Role role) {
        return switch (role) {
            case ADMIN -> new Admin();
            case RIDER -> new Rider();};
    }
}
