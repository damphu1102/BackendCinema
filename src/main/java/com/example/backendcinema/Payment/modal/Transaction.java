package com.example.backendcinema.Payment.modal;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vnpTxnRef; // Mã giao dịch tham chiếu tại hệ thống của bạn
    private String vnpTransactionNo; // Mã giao dịch tại VNPAY
    private Long amount;
    private String vnpResponseCode;
    private LocalDateTime payDate;
    private String orderInfo;
    private String vnpSecureHash;
}