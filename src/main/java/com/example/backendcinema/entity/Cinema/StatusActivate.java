package com.example.backendcinema.entity.Cinema;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusActivate {
    Activate("Hoạt động"),
    Unactivate("Không hoạt động");

    private final String activeVietnamese;

    StatusActivate(String activeVietnamese){
        this.activeVietnamese = activeVietnamese;
    }

    @JsonValue
    public String getActiveVietnamese(){
        return activeVietnamese;
    }
}
