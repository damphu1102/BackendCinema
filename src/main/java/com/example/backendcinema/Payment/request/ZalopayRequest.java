package com.example.backendcinema.Payment.request;

import lombok.Data;

import java.util.List;

@Data
public class ZalopayRequest {
    private int amount;
    private List<Integer> selectedSeats; // Thêm trường này để chứa danh sách ID ghế
    private String accountId;
    private String movieName;
    private String cinema;
    private List<String> seatNumber; // Danh sách số ghế (ví dụ: ["A1", "A2"])
    private List<String> room; // Thay đổi thành List<String>
    private String time;
    private String date;
}
