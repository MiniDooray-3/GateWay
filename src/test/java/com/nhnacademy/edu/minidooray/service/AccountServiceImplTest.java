package com.nhnacademy.edu.minidooray.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.minidooray.adapter.AccountAdaptor;
import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AccountServiceImplTest {


    @Autowired
    AccountService accountService;

    @MockBean
    AccountAdaptor accountAdaptor;

    @Test
    void testMatches() {
        given(accountAdaptor.matches(any())).willReturn(true);

        boolean result = accountService.matches("userId", "userPassword");

        assertTrue(result);

        verify(accountAdaptor, times(1)).matches(any());
    }

    @Test
    void testSignup() {
        doNothing().when(accountAdaptor).createUser(any());

        accountService.signUp(new SignupUser("tester", "email@email", "password"));

        verify(accountAdaptor, times(1)).createUser(any());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(accountAdaptor).deleteUser(anyString());

        accountService.deleteUser("tester");

        verify(accountAdaptor, times(1)).deleteUser(anyString());
    }
}