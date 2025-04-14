package com.example.backendcinema.entity.Movie;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenreMovie {
    Drama("Tâm lý"),
    Thriller("Kinh dị"),
    Action("Hành động"),
    Historical("Lịch sử"),
    Comedy("Hài"),
    Romantic("Lãng mạn"),
    Animation("Hoạt hình"),
    Psychology("Tâm lý học"),
    Horror("Kinh dị"),
    Mystery("Bí ẩn"),
    Adventure("Phiêu lưu"),
    Horrified("Kinh hoàng");

    private final String genreVietnamese;

    GenreMovie(String genreVietnamese) {
        this.genreVietnamese = genreVietnamese;
    }

    @JsonValue
    public String getGenreVietnamese() {
        return genreVietnamese;
    }
}
