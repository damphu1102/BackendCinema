package com.example.backendcinema.Payment.repository;

import com.example.backendcinema.Payment.modal.OrderIntermediate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderIntermediateRepository extends JpaRepository<OrderIntermediate,Integer> {
    List<OrderIntermediate> findByAppTransId(String appTransId);
}
