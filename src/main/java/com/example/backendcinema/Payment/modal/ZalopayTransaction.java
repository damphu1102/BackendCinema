package com.example.backendcinema.Payment.modal;

import com.example.backendcinema.Converter.StringListConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.List;

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
    private String accountId;
    private String movieName;
    private String cinema;
    @Convert(converter = StringListConverter.class)
    @Column(name = "seat_number_list")
    private List<String> seatNumberList;

    private String room;
    private String time;
    private String date;
}