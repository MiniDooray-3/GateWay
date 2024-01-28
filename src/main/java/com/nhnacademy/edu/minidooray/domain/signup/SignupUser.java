package com.nhnacademy.edu.minidooray.domain.signup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignupUser {
    @NotBlank
    String id;

    @Email
    String email;

    @NotBlank
    String password;

}
