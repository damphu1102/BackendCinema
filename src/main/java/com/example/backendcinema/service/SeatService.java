package com.example.backendcinema.service;

import com.example.backendcinema.entity.Seat.Seat;

import com.example.backendcinema.entity.Seat.SeatStatus;
import modal.Seat.SeatRowReq;

import java.util.List;

public interface SeatService {
    List<Seat> getAll();

    List<Seat> filterRow(SeatRowReq request);

    Seat updateSeatStatus(int seat_id, SeatStatus newStatus);
}
