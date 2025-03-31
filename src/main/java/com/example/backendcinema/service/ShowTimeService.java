package com.example.backendcinema.service;

import com.example.backendcinema.entity.ShowTime;

import java.time.LocalDate;
import java.util.List;

public interface ShowTimeService {
    List<ShowTime> getALl();

    List<ShowTime> filter(LocalDate date);
}
