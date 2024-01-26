package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;

public interface AccountService {

    boolean matches(String userId, String userPassword);

    void signUp(SignupUser signupUser);

    void deleteUser(String userId);
}
