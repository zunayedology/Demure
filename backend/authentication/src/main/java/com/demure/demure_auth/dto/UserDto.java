package com.demure.demure_auth.dto;

import com.demure.demure_auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private Date dateOfBirth;
    private Date dateOfRegistration;
    private Role role;
}
