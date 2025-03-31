package com.example.backendcinema.service.Impl;

import com.example.backendcinema.entity.ServiceMovie;
import com.example.backendcinema.repository.ServiceMovieRepository;
import com.example.backendcinema.service.ServiceMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceMovieServiceImpl implements ServiceMovieService {

    @Autowired
    private ServiceMovieRepository serviceMovieRepository;

    @Override
    public List<ServiceMovie> getAll() {
        return serviceMovieRepository.findAll();
    }
}
