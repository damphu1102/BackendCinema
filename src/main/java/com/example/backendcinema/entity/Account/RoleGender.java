package com.example.backendcinema.entity.Account;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleGender {
    Nam("Nam"),
    Nu("Nữ");

    private final String genderVietnamese;

    RoleGender(String genderVietnamese) {
        this.genderVietnamese = genderVietnamese;
    }

    @JsonValue
    public String getGenderVietnamese() {
        return genderVietnamese;
    }
}
