package com.example.backendcinema.repository;

import com.example.backendcinema.entity.ServiceMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMovieRepository extends JpaRepository<ServiceMovie, Integer>, JpaSpecificationExecutor<ServiceMovie> {
}
