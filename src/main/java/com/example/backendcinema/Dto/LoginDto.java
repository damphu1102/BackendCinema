package com.example.backendcinema.Dto;

import com.example.backendcinema.entity.Account.RoleAccount;
import com.example.backendcinema.entity.Account.RoleGender;
import lombok.Data;

import java.time.LocalDate;

@Data

public class LoginDto {
    private String userName;
    private int accountId;
    private String fullName;
    private RoleAccount roleAccount;
    private String emailAccount;
    private String phoneNumber;
    private String city;
    private String district;
    private String address;
    private LocalDate dateBird;
    private RoleGender roleGender;
    private String token;
}
