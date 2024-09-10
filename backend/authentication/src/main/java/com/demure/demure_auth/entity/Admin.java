package com.demure.demure_auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "adminId")
public class Admin extends User{
}
