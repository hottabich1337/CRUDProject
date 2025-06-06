package com.example.CRUDProject.enums;

public enum OrderStatus {
    NEW,
    PROCESSING,
    COMPLETED,
    CANCELLED;

    public static OrderStatus fromString(String status) {
        try {
            return OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверный статус: " + status);
        }
    }

}
