package com.example.backendcinema.entity.Account;

import org.springframework.security.core.GrantedAuthority;

public enum RoleAccount implements GrantedAuthority {
    Admin, Manager, User;

    @Override
    public String getAuthority() {
        return name();
    }
}
