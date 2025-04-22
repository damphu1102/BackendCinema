package com.example.backendcinema.entity.Movie;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LanguageMovie {
    Vietnamese("Tiếng Việt"),
    English("Tiếng Anh"),
    Chinese("Tiếng Trung"),
    Korean("Tiếng Hàn");

    private final String languageVietnamese;

    LanguageMovie(String languageVietnamese){
        this.languageVietnamese = languageVietnamese;
    }

    @JsonValue
    public String getLanguageVietnamese(){
        return languageVietnamese;
    }
}
