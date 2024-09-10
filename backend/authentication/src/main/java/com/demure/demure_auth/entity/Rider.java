package com.demure.demure_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "riders")
@PrimaryKeyJoinColumn(name = "riderId")
public class Rider extends User{
    private String nationalId;
}
