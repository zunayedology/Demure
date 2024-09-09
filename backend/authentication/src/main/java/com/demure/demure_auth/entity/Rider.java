package com.demure.demure_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "riders")
@PrimaryKeyJoinColumn(name = "riderId")
public class Rider extends User{
    @MapsId
    @OneToOne
    @JoinColumn(name = "riderId")
    private User user;
}
