package com.example.backendcinema.zaloPay.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private String orderUrl;
    private String returnCode;
    private String returnMessage;


}
