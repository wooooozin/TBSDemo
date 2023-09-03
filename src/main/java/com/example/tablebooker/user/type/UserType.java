package com.example.tablebooker.user.type;

public enum UserType {
    GENERAL_USER("일반 사용자"),
    PARTNER("파트너");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
