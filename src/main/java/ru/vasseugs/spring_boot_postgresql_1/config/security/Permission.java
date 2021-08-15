package ru.vasseugs.spring_boot_postgresql_1.config.security;

public enum Permission {

    READ("guitars:read"),
    WRITE("guitars:write"),
    DELETE("guitars:delete"),
    EDIT("guitars:edit");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
