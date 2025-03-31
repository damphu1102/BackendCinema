package com.example.backendcinema.Specification.Movie;

import com.example.backendcinema.entity.Movie.Movie;
import modal.Movie.MovieStatusReq;
import org.springframework.data.jpa.domain.Specification;

public class StatusSpecification{
    public static Specification<Movie> buildCondition(MovieStatusReq request){
        return Specification.where(buildWithStatus(request));
    }

    private static Specification<Movie> buildWithStatus(MovieStatusReq request) {
        if (request.getStatusMovie() != null) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("statusMovie"), request.getStatusMovie());
            };
        } else {
            return null;
        }
    }
}
