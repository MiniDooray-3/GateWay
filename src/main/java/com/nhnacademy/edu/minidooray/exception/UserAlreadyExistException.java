package com.nhnacademy.edu.minidooray.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String userId) {
        super("이미 존재하는 회원 아이디 입니다." + userId);
    }
}
