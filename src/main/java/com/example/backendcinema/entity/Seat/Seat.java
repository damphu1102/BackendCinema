package com.example.backendcinema.entity.Seat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SEAT")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEAT_ID")
    private int seat_id;

    @Column(name = "SEAT_NUMBER", nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEAT_ROW", nullable = false)
    private SeatRow seatRow;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEAT_STATUS", nullable = false)
    private SeatStatus seatStatus ;

    @Column(name = "IMG_UNSELECTED", length = 255, nullable = false)
    private String imgUnselected;

    @Column(name = "IMG_SELECTED",length = 255, nullable = false)
    private String imgSelected;

    @Column(name = "SEAT_PRICE")
    private int seatPrice;
}
