package com.example.backendcinema.controller;

import com.example.backendcinema.Dto.Account.AccountCreateDto;
import com.example.backendcinema.Dto.Account.AccountUpdateDto;
import com.example.backendcinema.Dto.Account.UpdatePassDto;
import com.example.backendcinema.Dto.Account.UpdatePassEmailDto;
import com.example.backendcinema.entity.Account.Account;
import com.example.backendcinema.service.AccountService;
import modal.LoginRequest;
import modal.PassRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")

public class AccountController {
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasAnyAuthority('Admin') or hasAuthority('Manager')")
    @GetMapping
    public List<Account> getAllAccount(){
      return accountService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('User') or hasAuthority('Admin')")
    @GetMapping("/{accountId}")
    public Account findById (@PathVariable int accountId){
        return accountService.findById(accountId);
    }

    @GetMapping("/check_username")
    public boolean checkUsernameAvailability(@RequestParam String username) {
        return accountService.isUsernameExists(username);
    }

    @PostMapping("/check_pass")
    public boolean checkPass(@RequestBody PassRequest request) {
        return accountService.checkPass(request.getAccountId(), request.getPassWord());
    }

    @PostMapping("/authenticateUser")
    public boolean authenticateUser(@RequestBody LoginRequest request) {
        return accountService.authenticateUser(request.getUserName(), request.getPassWord());
    }

    @PostMapping("/authenticateAdmin")
    public boolean authenticateAdmin(@RequestBody LoginRequest request) {
        return accountService.authenticateAdmin(request.getUserName(), request.getPassWord());
    }

    @PostMapping("/create")
    public Account create(@RequestBody AccountCreateDto dto){
        return accountService.create(dto);
    }

    @PreAuthorize("hasAnyAuthority('User')")
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer accountId, @RequestBody AccountUpdateDto dto) {
        dto.setAccountId(accountId); // Đảm bảo accountId trong dto trùng với id trên path
        Account updatedAccount = accountService.update(dto);
        if (updatedAccount != null) {
            return ResponseEntity.ok(updatedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('User')")
    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePassDto dto) {
        try {
            accountService.updatePass(dto);
            return ResponseEntity.ok("Password updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PreAuthorize("hasAnyAuthority('User')")
    @PutMapping("/updatePasswordEmail")
    public ResponseEntity<String> updatePasswordEmail(@RequestBody UpdatePassEmailDto dto) {
        try {
            accountService.updatePassEmail(dto);
            return ResponseEntity.ok("Password updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

