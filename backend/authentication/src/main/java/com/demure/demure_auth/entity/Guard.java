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
@Table(name = "guards")
@PrimaryKeyJoinColumn(name = "guardId")
public class Guard extends User{
    private String nationalId;
    private Long stationId;

    public Guard(String username,
                 String password,
                 String phoneNumber,
                 String email,
                 Date dateOfBirth,
                 Date dateOfRegistration,
                 String nationalId,
                 Long stationId) {
        super(
                username,
                password,
                phoneNumber,
                email,
                dateOfBirth,
                dateOfRegistration,
                Role.GUARD
        );
        this.nationalId = nationalId;
        this.stationId = stationId;
    }
}
