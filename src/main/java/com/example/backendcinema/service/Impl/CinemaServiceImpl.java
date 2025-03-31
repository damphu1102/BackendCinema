package com.example.backendcinema.service.Impl;

import com.example.backendcinema.Dto.Cinema.CinemaDtoCreate;
import com.example.backendcinema.Dto.Cinema.CinemaDtoUpdate;
import com.example.backendcinema.Specification.Cinema.LocationSpecification;
import com.example.backendcinema.entity.Cinema.Cinema;
import com.example.backendcinema.repository.CinemaRepository;
import com.example.backendcinema.service.CinemaService;
import modal.Cinema.CinemaLocationReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema create(CinemaDtoCreate dto) {
        Cinema cinema = new Cinema();
        BeanUtils.copyProperties(dto, cinema);
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema update(CinemaDtoUpdate dto) {
        Cinema cinema = new Cinema();
        BeanUtils.copyProperties(dto, cinema);
        return cinemaRepository.save(cinema);
    }

    @Override
    public void delete(int cinemaId) {
        cinemaRepository.deleteById(cinemaId);
    }

    @Override
    public List<Cinema> filter(CinemaLocationReq request) {
        Specification<Cinema> specification = LocationSpecification.buildCondition(request);
        return cinemaRepository.findAll(specification);
    }
}
