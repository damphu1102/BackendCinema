package com.example.backendcinema.Payment.modal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zalopay_transactions")
@Getter
@Setter
public class ZalopayTransaction {

    @Id
    private String appTransId;  // ID giao dịch từ ZaloPay

    private int amount;          // Số tiền thanh toán
    private String status;       // Trạng thái thanh toán (ví dụ: "Success", "Failed")
    private String message;      // Thông điệp trả về
    private long timestamp;      // Thời gian trả về kết quả

    public ZalopayTransaction orElse(Object o) {
        return null;
    }
}