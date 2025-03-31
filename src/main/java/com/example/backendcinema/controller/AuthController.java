package com.example.backendcinema.controller;

import com.example.backendcinema.Dto.LoginDto;
import com.example.backendcinema.Utils.JWTTokenUtils;
import com.example.backendcinema.entity.Account.Account;
import com.example.backendcinema.repository.AccountRepository;
import modal.LoginRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

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
}
