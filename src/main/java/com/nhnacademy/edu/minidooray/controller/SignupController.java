package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;
import com.nhnacademy.edu.minidooray.exception.UserAlreadyExistException;
import com.nhnacademy.edu.minidooray.exception.ValidationFailedException;
import com.nhnacademy.edu.minidooray.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SignupController {

    private final AccountService accountService;

    public SignupController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signupForm";
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String doSignup(@Validated @ModelAttribute SignupUser signupUser,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        accountService.signUp(signupUser);

        return "redirect:/";
    }
}
