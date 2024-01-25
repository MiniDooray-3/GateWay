package com.nhnacademy.edu.minidooray.domain.signup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignupUser {
    @NotBlank
    String userId;

    @NotBlank
    String userPassword;

    @Email
    String email;
}
