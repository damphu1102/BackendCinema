package com.example.backendcinema.service.Impl;

import com.example.backendcinema.Dto.Movie.MovieDtoCreate;
import com.example.backendcinema.Dto.Movie.MovieDtoUpdate;
import com.example.backendcinema.Specification.Movie.MovieSearchAndPaginationSpecification;
import com.example.backendcinema.Specification.Movie.MovieSearchSpecification;
import com.example.backendcinema.Specification.Movie.StatusSpecification;
import com.example.backendcinema.entity.Movie.Movie;
import com.example.backendcinema.repository.MovieRepository;
import com.example.backendcinema.service.MovieService;
import modal.Movie.MovieSearchReq;
import modal.Movie.MovieSearchReqAndPagination;
import modal.Movie.MovieStatusReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId); // là 1 đối tượng chống null
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        }
        return null;
    }

    @Override
    public Movie create(MovieDtoCreate dto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(dto, movie);
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(MovieDtoUpdate dto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(dto, movie);
        return movieRepository.save(movie);
    }

    @Override
    public void delete(int movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public Page<Movie> search(MovieSearchReqAndPagination request) {
//        Đk tìm kiếm tổng hợp
        Specification<Movie> specification = MovieSearchAndPaginationSpecification.buildCondition(request);

//         Đk phân trang
        PageRequest pageRequest;
        if ("DESC".equals(request.getSortType())){
            // Giá trị page mà thư viện mong muốn để vào trang đầu tiên: 0
            // Giá trị mình muốn để lấy trang đầu tiên: 1 - 1
            pageRequest = PageRequest.of(request.getPage() - 1, request.getPageSize(), Sort.by(request.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getPageSize(), Sort.by(request.getSortField()).ascending());
        }

        // Tìm kiếm phim với điều kiện và phân trang
        Page<Movie> moviePage = movieRepository.findAll(specification, pageRequest);
        request.setTotalPages(moviePage.getTotalPages());

        return moviePage;
    }

    @Override
    public List<Movie> filter(MovieStatusReq request) {
        Specification<Movie> specification = StatusSpecification.buildCondition(request);
        return movieRepository.findAll(specification);
    }

    @Override
    public List<Movie> searchList(MovieSearchReq request) {
        Specification<Movie> specification = MovieSearchSpecification.buildCondition(request);

        return movieRepository.findAll(specification);
    }
}
