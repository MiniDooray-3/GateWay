package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.login.LoginUser;
import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;

public interface AccountAdaptor {


    boolean matches(LoginUser user);

    boolean createUser(SignupUser signupUser);

    boolean isExistUser(String userId);
}
