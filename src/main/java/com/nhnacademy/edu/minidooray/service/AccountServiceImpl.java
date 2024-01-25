package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.AccountAdapter;
import com.nhnacademy.edu.minidooray.domain.User;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountAdapter accountAdapter;


    public AccountServiceImpl(AccountAdapter accountAdapter) {
        this.accountAdapter = accountAdapter;
    }


    public boolean matches(String userId, String userPassword) {
        return accountAdapter.matches(new User(userId, userPassword));
    }
}
