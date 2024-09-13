package com.demure.demure_auth.entity;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    RIDER("ROLE_RIDER");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

}
