package com.example.backendcinema.repository;

import com.example.backendcinema.entity.Cinema.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer>, JpaSpecificationExecutor<Cinema> {
}
