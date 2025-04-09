package com.example.backendcinema.Payment.Controller;

import com.example.backendcinema.Payment.Service.ZalopayService;
import com.example.backendcinema.Payment.request.ZalopayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/zalopay")
public class ZalopayController {

    @Autowired
    private ZalopayService zalopayService;

    @PostMapping
    public ResponseEntity<String> createZalopayOrder(@RequestBody ZalopayRequest zaloPayRequest) {
        String response = zalopayService.createOrder(zaloPayRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order-status/{appTransId}")
    public ResponseEntity<String> getOrderStatus(@PathVariable String appTransId) {
        String response = zalopayService.getOrderStatus(appTransId);
        return ResponseEntity.ok(response);
    }

}
