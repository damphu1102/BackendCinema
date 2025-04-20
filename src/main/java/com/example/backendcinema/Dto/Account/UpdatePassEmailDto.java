package com.example.backendcinema.Dto.Account;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePassEmailDto {
    private String emailAccount;
    @NotBlank
    private String newPassWord;
}
