package com.demure.demure_auth.utility;

import com.demure.demure_auth.dto.UserDto;
import com.demure.demure_auth.entity.User;
import com.demure.demure_auth.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public final UserFactory userFactory;

    public UserMapper(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassword(),
                user.getDateOfBirth(),
                user.getDateOfRegistration(),
                user.getRole()
        );
    }

    public User toUser(UserDto userDto) {
        User user = userFactory.createUser(userDto.getRole());
        user.setId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setDateOfRegistration(userDto.getDateOfRegistration());
        user.setRole(userDto.getRole());
        return user;
    }
}