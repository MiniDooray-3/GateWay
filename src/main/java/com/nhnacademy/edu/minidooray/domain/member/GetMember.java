package com.nhnacademy.edu.minidooray.domain.member;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class GetMember {
    private String memberRole;
    private String memberId; // user_id
    
}