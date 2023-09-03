package com.example.tablebooker.reservation.type;

public enum ReservationAvailabilityStatus {
    AVAILABLE("예약 가능"),
    UNAVAILABLE("예약 불가");

    private final String description;

    ReservationAvailabilityStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
