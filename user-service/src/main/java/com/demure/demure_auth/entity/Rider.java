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
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private Date dateOfRegistration;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Long height;

    @Column(nullable = false)
    private Long weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;
}
