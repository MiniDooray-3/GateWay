package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler(HttpClientErrorException.class)

    public String handleClientException() {
        return "redirect:/access-denied";
    }

    @ExceptionHandler(HttpServerErrorException.class)

    public String handleServerException() {
        return "redirect:/";
    }

    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(Exception exception, Model model) {
        model.addAttribute("exception", exception);

        return "error";
    }
}

