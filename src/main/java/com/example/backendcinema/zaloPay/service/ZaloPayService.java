package com.example.backendcinema.zaloPay.service;

import com.example.backendcinema.zaloPay.dto.OrderRequest;
import com.example.backendcinema.zaloPay.dto.OrderResponse;

public interface ZaloPayService {
    OrderResponse createZaloPayOrder(OrderRequest orderRequest);
}
