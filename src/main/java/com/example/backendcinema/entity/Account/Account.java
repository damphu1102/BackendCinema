package com.example.backendcinema.entity.Account;

import com.example.backendcinema.Converter.RoleGenderConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table (name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ACCOUNT_ID")
    private int accountId;

    @Column(name = "USER_NAME", length = 20, nullable = false, unique = true)
    private String userName;

    @Column(name = "FULL_NAME", length = 100, nullable = false)
    private String fullName;

    @Column(name = "EMAIL", length = 255, nullable = false, unique = true)
    private String emailAccount;

    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String passWord;

    @Column(name = "PHONE", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "CITY", length = 30)
    private String city;

    @Column(name = "DISTRICT", length = 20)
    private String district;

    @Column(name = "ADDRESS", length = 20)
    private String address;

    @Column(name = "DATE_BIRD")
    private LocalDate dateBird;

    @Convert(converter = RoleGenderConverter.class)
    @Column(name = "ROLE_GENDER")
    private RoleGender roleGender;

    @Column(name = "ROLE_ACCOUNT", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleAccount roleAccount;

}
