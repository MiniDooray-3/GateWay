package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.service.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AccountService accountService;


    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("userId") String userId,
                          @RequestParam("userPassword") String userPassword, HttpServletRequest request) {

        if (accountService.matches(userId, userPassword)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("LOGIN_ID", userId);

            return "redirect:/";
        }

        return "redirect:/login";
    }
}
