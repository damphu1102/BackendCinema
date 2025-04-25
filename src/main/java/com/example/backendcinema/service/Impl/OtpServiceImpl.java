package com.example.backendcinema.service.Impl;

import com.example.backendcinema.entity.Otp;
import com.example.backendcinema.repository.OtpRepository;
import com.example.backendcinema.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    private static final Logger logger = LoggerFactory.getLogger(OtpServiceImpl.class);

    private final JavaMailSender mailSender;
    private final OtpRepository otpRepository;
    private final Random random = new Random();

    @Autowired
    public OtpServiceImpl(JavaMailSender mailSender, OtpRepository otpRepository) {
        this.mailSender = mailSender;
        this.otpRepository = otpRepository;
    }

    @Override
    @Transactional
    public void sendOtpToEmail(String email) {
        logger.info("Bắt đầu gửi mã OTP đến email: {}", email);
        // Xóa OTP cũ nếu có
        otpRepository.deleteByEmail(email);
        logger.info("Đã xóa OTP cũ cho email: {}", email);

        String otp = generateOtp();
        String subject = "Mã OTP của bạn";
        String body = "Mã OTP của bạn là: " + otp +
                "\n\nMã này sẽ hết hiệu lực trong vòng 03 phút.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        try {
            mailSender.send(message);
            System.out.println("Email đã được gửi thành công đến: " + email);
            logger.info("Email chứa mã OTP đã được gửi thành công đến: {}", email);

            // Lưu OTP vào cơ sở dữ liệu sau khi gửi thành công (hoặc trước đó tùy logic)
            Otp otpEntity = new Otp();
            otpEntity.setEmail(email);
            otpEntity.setOtpCode(otp);
            otpEntity.setCreatedTime(LocalDateTime.now());
            otpRepository.save(otpEntity);
            logger.info("Đã lưu mã OTP {} cho email {} vào database.", otp, email);

        } catch (MailException e) {
            logger.error("Lỗi khi gửi email đến {}: {}", email, e.getMessage());
            // Xử lý lỗi gửi email ở đây
            throw new RuntimeException("Không thể gửi email. Vui lòng thử lại sau.");
        }
    }

    private String generateOtp() {
        String otp = String.format("%06d", random.nextInt(1000000));
        logger.info("Đã tạo mã OTP: {}", otp);
        return otp;
    }

    @Override
    public boolean verifyOtp(String email, String otpCode) {
        logger.info("Bắt đầu xác thực mã OTP {} cho email: {}", otpCode, email);
        Optional<Otp> otpEntity = otpRepository.findByEmailAndOtpCode(email, otpCode);
        boolean isValid = otpEntity.isPresent() && !otpEntity.get().getCreatedTime().plusMinutes(3).isBefore(LocalDateTime.now());
        logger.info("Kết quả xác thực mã OTP {} cho email {}: {}", otpCode, email, isValid);
        return isValid;
    }
}
