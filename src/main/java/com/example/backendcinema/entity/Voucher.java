package com.example.backendcinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "VOUCHER")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOUCHER_ID")
    private int voucherId;

    @Column(name = "NAME_VOUCHER", length = 100, nullable = false)
    private String nameVoucher;

    @Column(name = "PRICE_VOUCHER", nullable = false)
    private int priceVoucher;
}
