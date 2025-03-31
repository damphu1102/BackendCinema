package com.example.backendcinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table (name = "SERVICE")

public class ServiceMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_ID")
    private int serviceId;

    @Column(name = "NAME_SERVICE", length = 100, nullable = false)
    private String nameService;

    @Column(name = "DESCRIPTION_SERVICE", length = 255, nullable = false)
    private String descriptionService;

    @Column(name = "PRICE_SERVICE", nullable = false)
    private int priceService;


}
