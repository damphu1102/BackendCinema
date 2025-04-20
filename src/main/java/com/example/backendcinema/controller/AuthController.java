package com.example.backendcinema.controller;

import com.example.backendcinema.Dto.LoginDto;
import com.example.backendcinema.Utils.JWTTokenUtils;
import com.example.backendcinema.entity.Account.Account;
import com.example.backendcinema.repository.AccountRepository;
import com.example.backendcinema.service.OtpService;
import modal.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Validated
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Autowired
    private OtpService otpService;

    @PostMapping("/login")
    public LoginDto loginJWT(@RequestBody @Valid LoginRequest request){
//        Check tồn tại của username
        Optional<Account> optional = accountRepository.findFirstByUserName(request.getUserName());
        if (optional.isEmpty()){
            return null; // thay bằng bắn ra lỗi
        }
        Account account = optional.get();
//        So sánh password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //mã hóa password
        boolean isPassWord = encoder.matches(request.getPassWord(), account.getPassWord());
        if (!isPassWord){
            return null; // thay bằng bắn ra lỗi
        }
//        Tạo ra đối tượng loginDto (tạo ra token)
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account, loginDto);
//        Tạo ra chuỗi token
        String token = jwtTokenUtils.createAccessToken(loginDto);
        loginDto.setToken(token);
        return loginDto;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        logger.info("Nhận yêu cầu gửi mã OTP đến email: {}", email);
        try {
            otpService.sendOtpToEmail(email);
            return ResponseEntity.ok("Mã OTP đã được gửi đến email của bạn.");
        } catch (Exception e) {
            logger.error("Lỗi khi gửi mã OTP đến email {}: {}", email, e.getMessage());
            return ResponseEntity.internalServerError().body("Đã có lỗi xảy ra khi gửi mã OTP. Vui lòng thử lại sau.");
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        logger.info("Nhận yêu cầu xác thực mã OTP {} cho email: {}", otp, email);
        if (otpService.verifyOtp(email, otp)) {
            logger.info("Mã OTP {} cho email {} hợp lệ.", otp, email);
            return ResponseEntity.ok("Mã OTP hợp lệ. Tài khoản của bạn đã được xác thực.");
        } else {
            logger.warn("Mã OTP {} cho email {} không hợp lệ hoặc đã hết hạn.", otp, email);
            return ResponseEntity.badRequest().body("Mã OTP không hợp lệ hoặc đã hết hạn.");
        }
    }
}
