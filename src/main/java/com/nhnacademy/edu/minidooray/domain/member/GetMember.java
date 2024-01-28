package com.nhnacademy.edu.minidooray.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetMember {
    private String memberRole;
    private String memberId; // user_id

}