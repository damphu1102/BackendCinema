package com.example.backendcinema.Payment.request;

import lombok.Data;

import java.util.List;

@Data
public class ZalopayRequest {
    private int amount;
    private List<Integer> selectedSeats; // Thêm trường này để chứa danh sách ID ghế
}
