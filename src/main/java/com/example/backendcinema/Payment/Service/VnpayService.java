package com.example.backendcinema.Payment.Service;

import com.example.backendcinema.Payment.request.VnpayRequest;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface VnpayService {
    String createPayment(VnpayRequest paymentRequest) throws UnsupportedEncodingException;
    ResponseEntity<String> handlePaymentReturn(String responseCode);
}
