package com.example.backendcinema.controller;

import com.example.backendcinema.Dto.Showtime.ShowtimeCreateDto;
import com.example.backendcinema.Dto.Showtime.ShowtimeUpdateDto;
import com.example.backendcinema.entity.ShowTime;
import com.example.backendcinema.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/showtime")
@CrossOrigin("*")
public class ShowTimeController {

    @Autowired
    private ShowTimeService showTimeService;

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('Manager')")
    @GetMapping
    public List<ShowTime> getAllShowTime(){
        return showTimeService.getALl();
    }

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/filter")
    public ResponseEntity<List<ShowTime>> getShowTimesByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<ShowTime> showTimes = showTimeService.filter(date);
        return ResponseEntity.ok(showTimes);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Manager')")
    @PostMapping("/create")
    public ShowTime create (@RequestBody ShowtimeCreateDto dto){
        return showTimeService.create(dto);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Manager')")
    @PutMapping("/update/{showtimeId}")
    public ShowTime update(@RequestBody ShowtimeUpdateDto dto){
        return showTimeService.update(dto);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Manager')")
    @DeleteMapping("/delete/{showtimeId}")
    public String delete(@PathVariable int showtimeId){
        showTimeService.delete(showtimeId);
        return "Xóa thành công";
    }
}
