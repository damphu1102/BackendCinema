package com.example.backendcinema.service;

public interface OtpService {
    void sendOtpToEmail(String email);
    boolean verifyOtp(String email, String otpCode);
}
