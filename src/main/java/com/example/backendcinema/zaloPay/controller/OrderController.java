package com.example.backendcinema.zaloPay.controller;

import com.example.backendcinema.zaloPay.dto.OrderRequest;
import com.example.backendcinema.zaloPay.dto.OrderResponse;
import com.example.backendcinema.zaloPay.service.ZaloPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private ZaloPayService zaloPayService;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        try {
            OrderResponse orderResponse = zaloPayService.createZaloPayOrder(orderRequest);
            return ResponseEntity.ok(orderResponse);
        } catch (Exception e) {
            // Xử lý lỗi và trả về mã trạng thái HTTP thích hợp
            OrderResponse errorResponse = new OrderResponse();
            errorResponse.setReturnCode("-1");
            errorResponse.setReturnMessage("Lỗi xử lý đơn hàng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
