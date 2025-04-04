package com.example.backendcinema.Dto.Account;

import com.example.backendcinema.entity.Account.RoleAccount;
import com.example.backendcinema.entity.Account.RoleGender;
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
    private RoleGender roleGender;
    private RoleAccount roleAccount;

}
