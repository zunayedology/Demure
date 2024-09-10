package com.demure.demure_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "adminId")
public class Admin extends User{
    public Admin(String username,
                 String password,
                 String phoneNumber,
                 String email,
                 Date dateOfBirth,
                 Date dateOfRegistration) {
        super(
                username,
                password,
                phoneNumber,
                email,
                dateOfBirth,
                dateOfRegistration,
                Role.ADMIN
        );
    }
}
