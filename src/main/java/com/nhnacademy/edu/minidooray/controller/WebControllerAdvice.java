package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.exception.UserAlreadyExistException;
import com.nhnacademy.edu.minidooray.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebControllerAdvice {


    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(Exception exception, Model model) {
        model.addAttribute("exception", exception);

        return "error";
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyExistException(Exception exception, Model model) {
        model.addAttribute("exception", exception);

        return "error";
    }
}
