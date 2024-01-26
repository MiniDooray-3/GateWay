package com.nhnacademy.edu.minidooray.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetMember {
    private String role;
    private String memberId; // user_id
}

