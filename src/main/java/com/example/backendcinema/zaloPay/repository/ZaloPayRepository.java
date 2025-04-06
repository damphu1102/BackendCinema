package com.example.backendcinema.zaloPay.repository;

import com.example.backendcinema.zaloPay.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaloPayRepository extends JpaRepository<Order, Long> {
}
