package com.example.backendcinema.service.Impl;

import com.example.backendcinema.entity.Banner;
import com.example.backendcinema.repository.BannerRespository;
import com.example.backendcinema.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRespository bannerRespository;

    @Override
    public List<Banner> getAll() {
        return bannerRespository.findAll();
    }
}
