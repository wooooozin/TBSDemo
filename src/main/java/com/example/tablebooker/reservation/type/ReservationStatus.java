package com.example.tablebooker.reservation.type;

public enum ReservationStatus {
    PENDING("예약 중"),
    APPROVED("승인됨"),
    DECLINED("거절됨");

    private final String description;

    ReservationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
