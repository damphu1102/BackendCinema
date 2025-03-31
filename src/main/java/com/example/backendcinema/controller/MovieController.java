package com.example.backendcinema.controller;

import com.example.backendcinema.Dto.Movie.MovieDtoCreate;
import com.example.backendcinema.Dto.Movie.MovieDtoUpdate;
import com.example.backendcinema.entity.Movie.Movie;
import com.example.backendcinema.service.MovieService;
import modal.Movie.MovieSearchReq;
import modal.Movie.MovieStatusReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService; // Làm việc với đối tượng interface

//    Tìm kiếm theo tên và phân trang
    @GetMapping("/search")
    public Page<Movie> findByMovie(MovieSearchReq request){
        return movieService.search(request);
    }

//    Lọc dữ liệu theo status
    @GetMapping("/filter")
    public List<Movie> filterMovie(MovieStatusReq request){return movieService.filter(request);}

    @GetMapping
    public List<Movie> getAllMovie(){
        return movieService.getAll();
    }

    @GetMapping("/{movieId}")
    public Movie findById(@PathVariable int movieId){
        return movieService.findById(movieId);
    }

    @DeleteMapping("/delete/{movieId}")
    public String delete(@PathVariable int movieId){
         movieService.delete(movieId);
         return "Xóa thành công";
    }

    @PostMapping("/create")
    public Movie create(@RequestBody MovieDtoCreate dto){
        return movieService.create(dto);
    }

    @PutMapping("/update/{movieId}")
    public Movie update(@RequestBody MovieDtoUpdate dto){
        return movieService.update(dto);
    }
}
