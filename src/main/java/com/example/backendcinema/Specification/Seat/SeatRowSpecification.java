package com.example.backendcinema.Specification.Seat;

import com.example.backendcinema.entity.Seat.Seat;
import modal.Seat.SeatRowReq;
import org.springframework.data.jpa.domain.Specification;

public class SeatRowSpecification {

    public static Specification<Seat> buildCondition(SeatRowReq request){
        return Specification.where(buildWithRow(request));
    }

    public  static Specification<Seat> buildWithRow(SeatRowReq request) {
        if (request.getSeatRow() != null){
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("seatRow"), request.getSeatRow());
            };
        } else {
            return null;
        }
    }
}
