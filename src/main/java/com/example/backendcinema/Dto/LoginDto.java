package com.example.backendcinema.Dto;

import com.example.backendcinema.entity.Account.RoleAccount;
import lombok.Data;

@Data

public class LoginDto {
    private String userName;
    private int accountId;
    private String fullName;
    private RoleAccount roleAccount;
    private String emailAccount;
    private String phoneNumber;
    private String token;
}
