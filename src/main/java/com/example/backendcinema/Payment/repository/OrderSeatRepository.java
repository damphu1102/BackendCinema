package com.example.backendcinema.Payment.repository;

import com.example.backendcinema.Payment.modal.OrderSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSeatRepository extends JpaRepository<OrderSeat,Integer> {
    List<OrderSeat> findByAppTransId(String appTransId);
}
