package com.example.backendcinema.Specification.Cinema;

import com.example.backendcinema.entity.Cinema.Cinema;
import modal.Cinema.CinemaLocationReq;

import org.springframework.data.jpa.domain.Specification;

public class LocationSpecification {

    public static Specification<Cinema> buildCondition(CinemaLocationReq request){
        return Specification.where(buildWithLocation(request));
    }

    private static Specification<Cinema> buildWithLocation(CinemaLocationReq request) {
        if (request.getLocationEnum() != null) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("locationEnum"), request.getLocationEnum());
            };
        } else {
            return null;
        }
    }
}
