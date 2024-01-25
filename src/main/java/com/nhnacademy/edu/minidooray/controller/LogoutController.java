package com.nhnacademy.edu.minidooray.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Optional.ofNullable(request.getSession())
                .ifPresent(HttpSession::invalidate);
        return "redirect:/";
    }
}
