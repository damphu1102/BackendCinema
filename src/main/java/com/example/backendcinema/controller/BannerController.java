package com.example.backendcinema.controller;

import com.example.backendcinema.entity.Banner;
import com.example.backendcinema.service.BannerService;
import com.example.backendcinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/banner")
@CrossOrigin("*")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping
    public List<Banner> getAllBanner(){return bannerService.getAll();}
}
