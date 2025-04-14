package com.example.backendcinema.entity.Cinema;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LocationEnum {
    HaNoi("Hà Nội"),
    HoChiMinh("Hồ Chí Minh"),
    DaNang("Đà Nẵng");

    private final String locationVietNamese;

    LocationEnum(String locationVietNamese) {
        this.locationVietNamese = locationVietNamese;
    }

    @JsonValue
    public String getLocationVietNamese() {
        return locationVietNamese;
    }
}
