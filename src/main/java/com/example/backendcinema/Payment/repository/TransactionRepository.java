package com.example.backendcinema.Payment.repository;

import com.example.backendcinema.Payment.modal.ZalopayTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<ZalopayTransaction, String> {
    List<ZalopayTransaction> findByAccountId(String accountId);

    List<ZalopayTransaction> findByAppTransId(String appTransId);
}
