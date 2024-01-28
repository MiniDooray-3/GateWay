package com.nhnacademy.edu.minidooray.domain.member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class RegisterMember {

    @NotBlank
    private String memberId; // user_id

    @NotNull
    private Long projectId;

    @NotBlank
    private String memberRole;
}