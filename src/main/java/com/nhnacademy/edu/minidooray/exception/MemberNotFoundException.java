package com.nhnacademy.edu.minidooray.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String memberId) {
        super("해당 멤버가 존재하지 않습니다." + memberId);
    }
}