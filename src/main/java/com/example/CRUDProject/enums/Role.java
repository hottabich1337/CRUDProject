package com.example.CRUDProject.enums;

public enum Role {
    ADMIN,USER,MODERATOR;

    public static Role fromString(String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверный статус: " + role);
        }
    }
}
