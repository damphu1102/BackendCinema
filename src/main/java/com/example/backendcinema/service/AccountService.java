package com.example.backendcinema.service;

import com.example.backendcinema.Dto.Account.AccountCreateDto;
import com.example.backendcinema.Dto.Account.AccountUpdateDto;
import com.example.backendcinema.Dto.Account.UpdatePassDto;
import com.example.backendcinema.Dto.Account.UpdatePassEmailDto;
import com.example.backendcinema.entity.Account.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    List<Account> getAll();

    Account findById (int accountId);

    Account create(AccountCreateDto dto);

    Account update(AccountUpdateDto dto);

    void updatePass (UpdatePassDto dto);

    void updatePassEmail(UpdatePassEmailDto dto);

    boolean isUsernameExists(String username);

    boolean checkPass(int accountId, String passWord);

    boolean authenticateUser(String username, String password);

    boolean authenticateAdmin(String username, String password);



}
