package com.example.backendcinema.service.Impl;

import com.example.backendcinema.Specification.Seat.SeatRowSpecification;
import com.example.backendcinema.entity.Seat.Seat;
import com.example.backendcinema.entity.Seat.SeatStatus;
import com.example.backendcinema.repository.SeatRepository;
import com.example.backendcinema.service.SeatService;
import modal.Seat.SeatRowReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getAll() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> filterRow(SeatRowReq request) {
        Specification<Seat> specification = SeatRowSpecification.buildCondition(request);
        return seatRepository.findAll(specification);
    }

    @Override
    public Seat updateSeatStatus(int seat_id, SeatStatus newStatus) {
        Optional<Seat> optionalSeat = seatRepository.findById(seat_id);
        if (optionalSeat.isPresent()) {
            Seat seat = optionalSeat.get();
            seat.setSeatStatus(newStatus);
            return seatRepository.save(seat);
        }
        return null;
    }

    @Transactional
    public void resetAllSeatStatusToUnselected() {
        List<Seat> seats = seatRepository.findAll();
        seats.forEach(seat -> {
            if (seat.getSeatStatus() != SeatStatus.success) {
                seat.setSeatStatus(SeatStatus.un_selected);
                seatRepository.save(seat);
            }
        });
    }
}
