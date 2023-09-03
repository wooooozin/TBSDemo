package com.example.tablebooker.store.type;

public enum StoreReservationStatus {
    AVAILABLE("예약 가능"),
    UNAVAILABLE("예약 불가능");

    private final String description;

    StoreReservationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
