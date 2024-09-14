package com.demure.demure_auth.service;

import com.demure.demure_auth.entity.UserDto;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    String authenticate(String username, String password);
    UserDto getUserById(Long userId);
    UserDto getCurrentUser();
}
