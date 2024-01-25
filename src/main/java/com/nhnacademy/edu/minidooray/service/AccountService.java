package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.User;

public interface AccountService {

    boolean matches(String userId, String userPassword);
}
