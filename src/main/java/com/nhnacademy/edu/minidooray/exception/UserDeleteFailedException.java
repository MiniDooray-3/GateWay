package com.nhnacademy.edu.minidooray.exception;

public class UserDeleteFailedException extends RuntimeException {

    public UserDeleteFailedException(String message) {
        super("유저 삭제에 실패했습니다.");
    }
}
