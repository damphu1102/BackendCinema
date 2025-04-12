package com.example.backendcinema.Payment.modal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zalopay_transactions")
@Getter
@Setter
public class ZalopayTransaction {

    @Id
    private String appTransId;
    private int amount;
    private int status;
    private String message;
    private String timestamp;
    }