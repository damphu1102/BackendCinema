package com.example.backendcinema.Dto.Account;

import lombok.Data;

@Data
public class UpdatePassDto {
    private int accountId;
    private String newPassWord;
}
