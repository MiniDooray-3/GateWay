package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.AccountAdaptor;
import com.nhnacademy.edu.minidooray.domain.User;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountAdaptor accountAdapter;


    public AccountServiceImpl(AccountAdaptor accountAdapter) {
        this.accountAdapter = accountAdapter;
    }


    public boolean matches(String userId, String userPassword) {
        return accountAdapter.matches(new User(userId, userPassword));
    }
}
