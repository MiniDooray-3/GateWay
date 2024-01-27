package com.nhnacademy.edu.minidooray.exception;

public class MemberAlreadyExistException extends RuntimeException {
    public MemberAlreadyExistException(String memberId) {
        super("이미 존재하는 멤버 입니다." + memberId);
    }
}
