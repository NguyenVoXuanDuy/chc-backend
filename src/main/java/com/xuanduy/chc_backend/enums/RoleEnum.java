package com.xuanduy.chc_backend.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    USER("USER", "User role"),
    ADMIN("ADMIN", "Admin role"),
    ;
    private final String name;
    private final String description;

    RoleEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
