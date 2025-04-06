package com.example.backendcinema.zaloPay.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private long amount;
    private String description;
}
