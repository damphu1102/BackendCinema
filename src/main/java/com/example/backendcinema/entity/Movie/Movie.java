package com.example.backendcinema.entity.Movie;


import com.example.backendcinema.Converter.GenreMovieConverter;
import com.example.backendcinema.Converter.LanguageMovieConverter;
import com.example.backendcinema.Converter.StatusMovieConverter;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table (name = "MOVIE")

public class Movie {
    @Id //Khai báo đây là khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Khai báo giá trị id tự tăng
    @Column(name = "MOVIE_ID") // Khai báo thuộc tính id trong Java tương ứng với cột ID tong DB
    private int movieId;

    @Column(name = "MOVIE_NAME", length = 100, nullable = false, unique = true)
    private String movieName;

    @Column(name = "ACTOR", length = 255, nullable = false)
    private String actor;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "DIRECTOR", length = 255, nullable = false)
    private String director;

    @Column(name = "IMAGE", length = 255, nullable = false)
    private String image;

    @Column(name = "TRAILER", length = 255, nullable = false)
    private String trailer;

    @Column(name = "RELEASE_DATE", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "RURATION", nullable = false)
    private int duration;

    @Convert(converter = LanguageMovieConverter.class)
    @Column(name = "LANGUAGE", nullable = false)
    private LanguageMovie language;

    @Convert(converter = GenreMovieConverter.class)
    @Column(name = "GENRE", nullable = false)
    private GenreMovie genre;

    @Column(name = "VIEWING_AGE", nullable = false)
    private int viewingAge;

    @Column(name = "CREATE_AT")
    private LocalDate createAt;

    @Column(name = "UPDATE_DATE")
    private LocalDate updateDate;

    @Convert(converter = StatusMovieConverter.class)
    @Column(name = "STATUS_MOVIE", nullable = false)
    private StatusMovie statusMovie;
}
