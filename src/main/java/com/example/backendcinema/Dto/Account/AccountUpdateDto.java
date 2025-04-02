package com.example.backendcinema.Dto.Account;

import com.example.backendcinema.entity.Account.RoleGender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountUpdateDto {
    private int accountId;
    private String emailAccount;
    private String phoneNumber;
    private String city;
    private String district;
    private String address;
    private LocalDate dateBird;
    private RoleGender roleGender;
}
