package com.example.backendcinema.Dto.Account;

import com.example.backendcinema.entity.Account.RoleAccount;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountCreateDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String fullName;
    @NotBlank
    private String emailAccount;
    @NotBlank
    private String passWord;
    @NotBlank
    private String phoneNumber;
    private RoleAccount roleAccount;

}
