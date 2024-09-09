package com.demure.demure_auth.entity;

import lombok.Getter;

@Getter
public enum RoleType {
    ADMIN("ROLE_ADMIN"),
    RIDER("ROLE_RIDER"),
    GUARD("ROLE_GUARD");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }
}
