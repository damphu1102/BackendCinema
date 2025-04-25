package com.example.backendcinema.service.Impl;

import com.example.backendcinema.Dto.Showtime.ShowtimeCreateDto;
import com.example.backendcinema.Dto.Showtime.ShowtimeUpdateDto;
import com.example.backendcinema.Specification.ShowTime.ShowTimeSpecification;
import com.example.backendcinema.entity.ShowTime;
import com.example.backendcinema.repository.ShowTimeRepository;
import com.example.backendcinema.service.ShowTimeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowTimeServiceImpl implements ShowTimeService {

    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Override
    public List<ShowTime> getALl() {
        return showTimeRepository.findAll();
    }

    @Override
    public List<ShowTime> filter(LocalDate date) {
        Specification<ShowTime> specification = ShowTimeSpecification.buildCondition(date);
        return showTimeRepository.findAll(specification);
    }

    @Override
    public ShowTime create(ShowtimeCreateDto dto) {
        ShowTime showTime = new ShowTime();
        BeanUtils.copyProperties(dto, showTime);
        return showTimeRepository.save(showTime);
    }

    @Override
    public ShowTime update(ShowtimeUpdateDto dto) {
        ShowTime showTime = new ShowTime();
        BeanUtils.copyProperties(dto, showTime);
        return showTimeRepository.save(showTime);
    }

    @Override
    public void delete(int showtimeId) {
        showTimeRepository.deleteById(showtimeId);
    }
}
