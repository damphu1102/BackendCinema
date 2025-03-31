package com.example.backendcinema.repository;

import com.example.backendcinema.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRespository extends JpaRepository<Banner, Integer> {
}
