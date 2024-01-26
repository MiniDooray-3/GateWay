package com.nhnacademy.edu.minidooray.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("해당 유저를 찾을 수 없습니다. " + userId);
    }
}
