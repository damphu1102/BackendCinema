package com.example.backendcinema.controller;

import com.example.backendcinema.entity.Seat.Seat;
import com.example.backendcinema.entity.Seat.SeatStatus;
import com.example.backendcinema.service.SeatService;
import modal.Seat.SeatRowReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
@CrossOrigin("*")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PreAuthorize("hasAuthority('User')")
    @GetMapping
    public List<Seat> getAllSeat() {
        return seatService.getAll();
    }

    @GetMapping("/filter")
    public List<Seat> filterRow(SeatRowReq request){return seatService.filterRow(request);}

    @PreAuthorize("hasAuthority('User')")
    @PutMapping("/{seat_id}/{newStatus}")
    public ResponseEntity<Seat> updateSeatStatus(
            @PathVariable int seat_id,
            @PathVariable SeatStatus newStatus) {

        Seat updatedSeat = seatService.updateSeatStatus(seat_id, newStatus);
        if (updatedSeat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedSeat);
    }
}
