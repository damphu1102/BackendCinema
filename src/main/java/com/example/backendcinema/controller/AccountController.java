package com.example.backendcinema.controller;

import com.example.backendcinema.Dto.Account.AccountCreateDto;
import com.example.backendcinema.Dto.LoginDto;
import com.example.backendcinema.entity.Account.Account;
import com.example.backendcinema.service.AccountService;
import modal.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")

public class AccountController {
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasAnyAuthority('Admin')")
    @GetMapping
    public List<Account> getAllAccount(){
      return accountService.getAll();
    }

    @GetMapping("/check_username")
    public boolean checkUsernameAvailability(@RequestParam String username) {
        return accountService.isUsernameExists(username);
    }

    @PostMapping("/authenticate")
    public boolean authenticate(@RequestBody LoginRequest request) {
        return accountService.authenticate(request.getUserName(), request.getPassWord());
    }

    @PostMapping("/create")
    public Account create(@RequestBody AccountCreateDto dto){
        return accountService.create(dto);
    }
}
