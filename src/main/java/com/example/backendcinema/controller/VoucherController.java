package com.example.backendcinema.controller;

import com.example.backendcinema.entity.Voucher;
import com.example.backendcinema.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/voucher")
@CrossOrigin("*")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @PreAuthorize("hasAuthority('User')")
    @GetMapping
    public List<Voucher> getAllVoucher(){
        return voucherService.getAll();
    }
}
