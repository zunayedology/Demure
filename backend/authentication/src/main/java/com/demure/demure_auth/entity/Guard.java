package com.demure.demure_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "guards")
@PrimaryKeyJoinColumn(name = "guardId")
public class Guard extends User{
    private String nationalId;
    private Long stationId;
}
