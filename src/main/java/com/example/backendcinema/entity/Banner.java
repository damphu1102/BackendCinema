package com.example.backendcinema.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "BANNER")

public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANNER_ID")
    private int bannerId;

    @Column(name = "IMAGE_URL", length = 255, nullable = false, unique = true)
    private String imageURL;

    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 500, nullable = false)
    private String description;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT")
    private LocalDateTime updateAt;
}
