package com.demure.demure_auth.utility;

import com.demure.demure_auth.dto.UserDto;
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
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public User toUser(@NotNull UserDto userDto) {
        User user = userFactory.createUser(userDto.getRole());
        user.setId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }
}
