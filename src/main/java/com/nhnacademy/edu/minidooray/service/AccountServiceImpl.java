package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.AccountAdaptor;
import com.nhnacademy.edu.minidooray.domain.login.LoginUser;
import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;
import com.nhnacademy.edu.minidooray.exception.UserAlreadyExistException;
import com.nhnacademy.edu.minidooray.exception.UserDeleteFailedException;
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
        boolean result = accountAdapter.createUser(signupUser);

        if (!result) {
            throw new UserAlreadyExistException(signupUser.getId());
        }
    }

    @Override
    public void deleteUser(String userId) {
        boolean result = accountAdapter.deleteUser(userId);

        if (!result) {
            throw new UserDeleteFailedException(userId);
        }
    }
}
