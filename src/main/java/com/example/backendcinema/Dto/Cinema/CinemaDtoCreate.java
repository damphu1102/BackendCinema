package com.example.backendcinema.Dto.Cinema;

import com.example.backendcinema.entity.Cinema.LocationEnum;
import lombok.Data;

@Data
public class CinemaDtoCreate {
    private String cinemaName;
    private LocationEnum locationEnum;
}
