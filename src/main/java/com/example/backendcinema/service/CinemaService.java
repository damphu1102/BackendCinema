package com.example.backendcinema.service;

import com.example.backendcinema.Dto.Cinema.CinemaDtoCreate;
import com.example.backendcinema.Dto.Cinema.CinemaDtoUpdate;
import com.example.backendcinema.entity.Cinema.Cinema;
import modal.Cinema.CinemaLocationReq;

import java.util.List;

public interface CinemaService {
    List<Cinema> getAll();

    Cinema create(CinemaDtoCreate dto);

    Cinema update(CinemaDtoUpdate dto);

    void delete(int cinemaId);

    List<Cinema> filter(CinemaLocationReq request);
}
