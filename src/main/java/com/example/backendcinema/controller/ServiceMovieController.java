package com.example.backendcinema.controller;

import com.example.backendcinema.entity.ServiceMovie;
import com.example.backendcinema.service.ServiceMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
@CrossOrigin("*")

public class ServiceMovieController {
    @Autowired
    private ServiceMovieService serviceMovieService;

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('Manager')")
    @GetMapping
    public List<ServiceMovie> getAllService(){
        return serviceMovieService.getAll();
    }
}
