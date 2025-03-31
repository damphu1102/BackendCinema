package com.example.backendcinema.controller;

import com.example.backendcinema.Dto.Account.AccountCreateDto;
import com.example.backendcinema.entity.Account.Account;
import com.example.backendcinema.service.AccountService;
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

    @PostMapping("/create")
    public Account create(@RequestBody AccountCreateDto dto){
        return accountService.create(dto);
    }
}
