package com.demure.demure_auth.entity;

import lombok.Getter;

@Getter
public enum Sex {
    MALE("SEX_MALE"),
    FEMALE("SEX_FEMALE"),
    OTHER("SEX_OTHER");

    private final String sexName;

    Sex(String sexName) {
        this.sexName = sexName;
    }
}
