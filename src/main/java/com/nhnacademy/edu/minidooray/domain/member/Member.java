package com.nhnacademy.edu.minidooray.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Member {
    private Long memberNumber;
    private Long projectId;
    private String memberId; // user_id
    private String memberRole;
}