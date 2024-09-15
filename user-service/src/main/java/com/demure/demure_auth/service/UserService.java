package com.demure.demure_auth.service;

import com.demure.demure_auth.entity.DTO;

public interface UserService {
    DTO registerUser(DTO DTO);
    String authenticate(String username, String password);
    DTO getUserById(Long userId);
    DTO getCurrentUser();
}
