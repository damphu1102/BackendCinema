package com.example.backendcinema.controller;

import com.example.backendcinema.Dto.Cinema.CinemaDtoCreate;
import com.example.backendcinema.Dto.Cinema.CinemaDtoUpdate;
import com.example.backendcinema.entity.Cinema.Cinema;
import com.example.backendcinema.service.CinemaService;
import modal.Cinema.CinemaLocationReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cinema")
@CrossOrigin("*")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PreAuthorize("hasAnyAuthority('Admin') or hasAuthority('Manager')")
    @GetMapping
    public List<Cinema> getAllCinema(){
        return cinemaService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('Admin') or hasAuthority('Manager')")
    @PostMapping("/create")
    public Cinema create(@RequestBody CinemaDtoCreate dto){
        return cinemaService.create(dto);
    }

    @PreAuthorize("hasAnyAuthority('Admin') or hasAuthority('Manager')")
    @PutMapping("/update/{cinemaId}")
    public Cinema update(@RequestBody CinemaDtoUpdate dto){
        return cinemaService.update(dto);
    }

    @PreAuthorize("hasAnyAuthority('Admin') or hasAuthority('Manager')")
    @DeleteMapping("/delete/{cinemaId}")
    public String delete(@PathVariable int cinemaId){
        cinemaService.delete(cinemaId);
        return "Xóa thành công";
    }

    @PreAuthorize("hasAnyAuthority('User')")
    @GetMapping("/filter")
    public List<Cinema> filterCinema(CinemaLocationReq request){
        return cinemaService.filter(request);
    }
}
