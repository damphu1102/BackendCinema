package com.example.backendcinema.service.Impl;

import com.example.backendcinema.Dto.Account.AccountCreateDto;
import com.example.backendcinema.Dto.Account.AccountUpdateDto;
import com.example.backendcinema.entity.Account.Account;
import com.example.backendcinema.entity.Account.RoleAccount;
import com.example.backendcinema.repository.AccountRepository;
import com.example.backendcinema.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(int accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()){
            return optionalAccount.get();
        }
        return null;
    }

    @Override
    public Account create(AccountCreateDto dto) {
        Account account = new Account();
        BeanUtils.copyProperties(dto, account);
        // Mã hóa mật khẩu sử dụng BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(dto.getPassWord());
        account.setPassWord(encodedPassword);
        account.setRoleAccount(RoleAccount.User);
        return accountRepository.save(account);
    }

    public Account update(AccountUpdateDto dto) {
        Account account = accountRepository.findById(dto.getAccountId()).orElse(null);

        if (account != null) {
            if (dto.getAddress() != null) {
                account.setAddress(dto.getAddress());
            }
            if (dto.getCity() != null) {
                account.setCity(dto.getCity());
            }
            if (dto.getDateBird() != null) {
                account.setDateBird(dto.getDateBird());
            }
            if (dto.getDistrict() != null) {
                account.setDistrict(dto.getDistrict());
            }
            if (dto.getEmailAccount() != null && !dto.getEmailAccount().equals("")) {
                account.setEmailAccount(dto.getEmailAccount());
            }
            if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().equals("")) {
                account.setPhoneNumber(dto.getPhoneNumber());
            }
            if (dto.getRoleGender() != null) {
                account.setRoleGender(dto.getRoleGender());
            }

            return accountRepository.save(account);
        } else {
            return null; // Hoặc throw exception
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        return accountRepository.findByUserName(username) != null;
    }

    public boolean authenticate(String username, String password) {
        Account account = accountRepository.findByUserName(username);
        if (account == null) {
            return false;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Tạo đối tượng mã hóa
        return passwordEncoder.matches(password, account.getPassWord()); // So sánh mật khẩu đã mã hóa
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optional = accountRepository.findFirstByUserName(username);
        if (optional.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        Account account = optional.get();
        RoleAccount role = account.getRoleAccount();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(role);
        User user = new User(account.getUserName(), account.getPassWord(), grantedAuthorities );
        return user;
    }
}
