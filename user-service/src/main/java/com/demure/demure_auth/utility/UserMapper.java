package com.demure.demure_auth.utility;

import com.demure.demure_auth.entity.UserDto;
import com.demure.demure_auth.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public final UserFactory userFactory;

    public UserMapper(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    public UserDto toUserDto(@NotNull User user) {
        return new
                UserDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }

    public User toUser(@NotNull UserDto userDto) {
        User user = userFactory.createUser(userDto.role());
        user.setId(userDto.userId());
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        user.setRole(userDto.role());
        return user;
    }
}
