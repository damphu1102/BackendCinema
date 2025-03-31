package com.example.backendcinema.entity.Cinema;

import com.example.backendcinema.entity.ShowTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "CINEMA")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CINEMA_ID")
    private int cinemaId;

    @Column(name = "CINEMA_NAME", length = 100, nullable = false, unique = true)
    private String cinemaName;

    @Enumerated(EnumType.STRING) // Lưu giá trị enum dưới dạng chuỗi trong database
    @Column(name = "LOCATION", nullable = false)
    private LocationEnum locationEnum;

//    @OneToMany(mappedBy = "cinema")
//    private List<ShowTime> showTimes;
}

