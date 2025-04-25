package com.example.backendcinema.service;

import com.example.backendcinema.Dto.Showtime.ShowtimeCreateDto;
import com.example.backendcinema.Dto.Showtime.ShowtimeUpdateDto;
import com.example.backendcinema.entity.ShowTime;

import java.time.LocalDate;
import java.util.List;

public interface ShowTimeService {
    List<ShowTime> getALl();

    List<ShowTime> filter(LocalDate date);

    ShowTime create(ShowtimeCreateDto dto);

    ShowTime update(ShowtimeUpdateDto dto);

    void delete(int  showtimeId);
}
