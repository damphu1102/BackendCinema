package com.example.backendcinema.entity.Movie;


import com.example.backendcinema.entity.ShowTime;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;


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

    @Column(name = "LANGUAGE", nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageMovie language;

    @Column(name = "RATING")
    private double rating;

    @Column(name = "GENRE", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreMovie genre;

    @Column(name = "VIEWING_AGE", nullable = false)
    private int viewingAge;

    @Column(name = "CREATE_AT")
    private LocalDate createAt;

    @Column(name = "UPDATE_DATE")
    private LocalDate updateDate;

    @Column(name = "STATUS_MOVIE", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusMovie statusMovie;

//    @OneToMany(mappedBy = "movie")
//    private List<ShowTime> showTimes;
}
