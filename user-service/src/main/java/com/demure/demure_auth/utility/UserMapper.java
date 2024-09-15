package com.demure.demure_auth.utility;

import com.demure.demure_auth.entity.DTO;
import com.demure.demure_auth.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public final UserFactory userFactory;

    public UserMapper(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    public DTO toUserDto(@NotNull User user) {
        return new
                DTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }

    public User toUser(@NotNull DTO DTO) {
        User user = userFactory.createUser(DTO.role());
        user.setId(DTO.userId());
        user.setUsername(DTO.username());
        user.setEmail(DTO.email());
        user.setPassword(DTO.password());
        user.setRole(DTO.role());
        return user;
    }
}
