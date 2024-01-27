package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.UserRequest;
import com.nhnacademy.edu.minidooray.domain.login.LoginUser;
import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;

public interface AccountAdaptor {


    boolean matches(LoginUser user);

    void createUser(SignupUser signupUser);

    UserRequest isExistUser(String userId);

    void deleteUser(String userId);
}
