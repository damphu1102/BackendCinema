package com.example.backendcinema.service;

import com.example.backendcinema.Dto.Movie.MovieDtoCreate;
import com.example.backendcinema.Dto.Movie.MovieDtoUpdate;
import com.example.backendcinema.entity.Movie.Movie;
import modal.Movie.MovieSearchReq;
import modal.Movie.MovieSearchReqAndPagination;
import modal.Movie.MovieStatusReq;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
//    Lấy ra tất cả movies
    List<Movie> getAll();

//    Tìm kiếm theo id
    Movie findById(int movie_id);

//    Thêm mới
    Movie create(MovieDtoCreate dto);

//    Sửa
    Movie update(MovieDtoUpdate dto);

//    Xóa
    void delete(int movie_id);

//    Tìm kiếm (theo tên) và phân trang
    Page<Movie> search(MovieSearchReqAndPagination request);

//    Lọc dữ liệu theo Status
    List<Movie> filter(MovieStatusReq request);

    //    Tìm kiếm (theo tên)
    List<Movie> searchList(MovieSearchReq request);

}
