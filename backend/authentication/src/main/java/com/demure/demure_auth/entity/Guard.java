package com.demure.demure_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "guards")
@PrimaryKeyJoinColumn(name = "guardId")
public class Guard extends User{
    @MapsId
    @OneToOne
    @JoinColumn(name = "guardId")
    private User user;

    @Column(nullable = false)
    private Long stationId;
}
