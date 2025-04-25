package com.example.backendcinema.Dto.Showtime;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class ShowtimeCreateDto {
    private LocalTime time;
    private LocalDate date;
    private String roomShow;
}
