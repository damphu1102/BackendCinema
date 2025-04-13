package com.example.backendcinema.Payment.modal;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_seat")
@Getter
@Setter
public class OrderSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String appTransId;

    private Integer seatId;

    public OrderSeat(String appTransId, Integer seatId) {
        this.appTransId = appTransId;
        this.seatId = seatId;
    }

    public OrderSeat() {
    }
}
