package com.example.backendcinema.repository;

import com.example.backendcinema.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer>, JpaSpecificationExecutor<Voucher> {
}
