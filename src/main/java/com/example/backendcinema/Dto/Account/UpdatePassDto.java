package com.example.backendcinema.Dto.Account;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePassDto {
    private int accountId;
    @NotBlank
    private String newPassWord;
}
