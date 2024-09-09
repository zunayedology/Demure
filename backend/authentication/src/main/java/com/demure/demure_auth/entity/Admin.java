package com.demure.demure_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "adminId")
public class Admin extends User{
    @MapsId
    @OneToOne
    @JoinColumn(name = "adminId")
    private User user;
}
