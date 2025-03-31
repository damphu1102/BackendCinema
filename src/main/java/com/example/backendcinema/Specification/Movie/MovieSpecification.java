package com.example.backendcinema.Specification.Movie;

import com.example.backendcinema.entity.Movie.Movie;
import modal.Movie.MovieSearchReq;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    public static Specification<Movie> buildCondition(MovieSearchReq request){
        return Specification.where(buildWithMovieName(request));
    }

    private static Specification<Movie> buildWithMovieName(MovieSearchReq request){
        if (request.getMovieName() != null && !"".equals(request.getMovieName())){
            return (root, query, cri) -> {
                return cri.like(root.get("movieName"), "%" + request.getMovieName() + "%");
            };
        } else {
            return null;
        }

    }
}
