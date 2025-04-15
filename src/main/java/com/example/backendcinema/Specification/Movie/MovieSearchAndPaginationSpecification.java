package com.example.backendcinema.Specification.Movie;

import com.example.backendcinema.entity.Movie.Movie;
import modal.Movie.MovieSearchReqAndPagination;
import org.springframework.data.jpa.domain.Specification;

public class MovieSearchAndPaginationSpecification {

    public static Specification<Movie> buildCondition(MovieSearchReqAndPagination request){
        return Specification.where(buildWithMovieName(request));
    }

    private static Specification<Movie> buildWithMovieName(MovieSearchReqAndPagination request){
        if (request.getMovieName() != null && !"".equals(request.getMovieName())){
            return (root, query, cri) -> {
                return cri.like(root.get("movieName"), "%" + request.getMovieName() + "%");
            };
        } else {
            return null;
        }

    }
}
