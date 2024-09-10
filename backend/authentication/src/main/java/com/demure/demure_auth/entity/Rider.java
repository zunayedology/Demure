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
@Table(name = "riders")
@PrimaryKeyJoinColumn(name = "riderId")
public class Rider extends User{
    private String nationalId;

    public Rider(String username,
                 String password,
                 String phoneNumber,
                 String email,
                 Date dateOfBirth,
                 Date dateOfRegistration,
                 String nationalId) {
        super(
                username,
                password,
                phoneNumber,
                email,
                dateOfBirth,
                dateOfRegistration,
                Role.RIDER
        );
        this.nationalId = nationalId;
    }
}
