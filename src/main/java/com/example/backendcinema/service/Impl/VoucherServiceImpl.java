package com.example.backendcinema.service.Impl;

import com.example.backendcinema.entity.Voucher;
import com.example.backendcinema.repository.VoucherRepository;
import com.example.backendcinema.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<Voucher> getAll() {
        return voucherRepository.findAll();
    }
}
