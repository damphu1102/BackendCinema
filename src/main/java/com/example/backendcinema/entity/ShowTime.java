package com.example.backendcinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "SHOWTIME")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOWTIME_ID")
    private int showtimeId;

    @Column(name = "SHOW_TIME",columnDefinition = "TIME")
    private LocalTime time;

    @Column(name = "SHOW_DATE", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "ROOM_SHOW", length = 10, nullable = false)
    private String roomShow;

//    @ManyToOne
//    @JoinColumn(name = "movie_id")
//    private Movie movie;
//
//    @ManyToOne
//    @JoinColumn(name = "cinema_id")
//    private Cinema cinema;
}
