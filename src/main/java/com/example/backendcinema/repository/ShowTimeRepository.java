package com.example.backendcinema.repository;

import com.example.backendcinema.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime,Integer>, JpaSpecificationExecutor<ShowTime> {
}
