package com.example.backendcinema.entity.Account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ACCOUNT_ID")
    private int accountId;

    @Column(name = "USER_NAME", length = 100, nullable = false)
    private String userName;

    @Column(name = "FULL_NAME", length = 100, nullable = false)
    private String fullName;

    @Column(name = "EMAIL", length = 255, nullable = false)
    private String emailAccount;

    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String passWord;

    @Column(name = "PHONE", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "ROLE_ACCOUNT", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleAccount roleAccount;

}
