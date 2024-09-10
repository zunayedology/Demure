package com.demure.demure_auth.dto;

import com.demure.demure_auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    private String username;
    private String phoneNumber;
    private String password;
    private String email;
    private String dateOfBirth;
    private Role role;
}
