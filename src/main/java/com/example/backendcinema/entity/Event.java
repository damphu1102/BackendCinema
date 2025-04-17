package com.example.backendcinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private int eventId;

    @Column(name = "TITLE_EVENT", length = 255, nullable = false)
    private String titleEvent;

    @Column(name = "IMAGE_EVENT", length = 255, nullable = false)
    private String imageEvent;

    @Column(name = "IMAGE_DETAIL", length = 255, nullable = false)
    private String imageDetail;

    @Column(name = "DESCRIPTION_EVENT", length = 10000, nullable = false)
    private String descriptionEvent;

    @Column(name = "CREATE_AT", columnDefinition = "DATE")
    private LocalDate createAT;
}
