package com.example.backendcinema.Payment.modal;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_intermediate")
@Getter
@Setter
public class OrderIntermediate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String appTransId;

    private Integer seatId;

    private String accountId;

    private String movieName;

    private String cinema;

    private String seatNumber;

    private String room;

    private String time;

    private String date;


    public OrderIntermediate(String appTransId, Integer seatId, String accountId,
                             String movieName, String cinema, String seatNumber,
                             String room, String time, String date) {
        this.appTransId = appTransId;
        this.seatId = seatId;
        this.accountId = accountId;
        this.movieName = movieName;
        this.cinema = cinema;
        this.seatNumber =seatNumber;
        this.room = room;
        this.time =time;
        this.date = date;
    }

    public OrderIntermediate() {
    }
}
