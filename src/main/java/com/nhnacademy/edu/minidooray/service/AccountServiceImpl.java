package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.AccountAdaptor;
import com.nhnacademy.edu.minidooray.domain.login.LoginUser;
import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountAdaptor accountAdapter;


    public AccountServiceImpl(AccountAdaptor accountAdapter) {
        this.accountAdapter = accountAdapter;
    }

    @Override
    public boolean matches(String userId, String userPassword) {
        return accountAdapter.matches(new LoginUser(userId, userPassword));
    }

    @Override
    public void signUp(SignupUser signupUser) {
        accountAdapter.createUser(signupUser);
    }

    @Override
    public void deleteUser(String userId) {
        accountAdapter.deleteUser(userId);
    }
}
