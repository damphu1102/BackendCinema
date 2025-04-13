package com.example.backendcinema.Payment.repository;

import com.example.backendcinema.Payment.modal.ZalopayTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<ZalopayTransaction, String> {
}
