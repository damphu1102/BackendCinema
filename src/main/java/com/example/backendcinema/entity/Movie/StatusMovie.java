package com.example.backendcinema.entity.Movie;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusMovie {
    showingNow("Đang chiếu"),
    comingSoon("Sắp chiếu"),
    specialScreening("Suất chiếu đặc biệt");

    private final String statusVietnamese;

    StatusMovie(String statusVietnamese) {
        this.statusVietnamese = statusVietnamese;
    }

    @JsonValue
    public String getStatusVietnamese() {
        return statusVietnamese;
    }
}
