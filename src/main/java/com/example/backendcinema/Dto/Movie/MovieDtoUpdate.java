package com.example.backendcinema.Dto.Movie;

import com.example.backendcinema.entity.Movie.GenreMovie;
import com.example.backendcinema.entity.Movie.LanguageMovie;
import com.example.backendcinema.entity.Movie.StatusMovie;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDtoUpdate {
    private int movieId;
    private String movieName;
    private String actor;
    private String director;
    private String image;
    private String trailer;
    private LocalDate releaseDate;
    private int duration;
    private LanguageMovie language;
    private double rating;
    private GenreMovie genre;
    private int viewingAge;
    private LocalDate createAt;
    private StatusMovie statusMovie;
    private String description;
    private LocalDate updateDate;




}
