package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.service.AccountService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserWithdrawController {

    private final AccountService accountService;

    public UserWithdrawController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/withdraw")
    public String userWithdraw(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (Objects.nonNull(session.getAttribute("LOGIN_ID"))) {
            String userId = (String) session.getAttribute("LOGIN_ID");

            accountService.deleteUser(userId);

            session.invalidate();
        }

        return "redirect:/";
    }
}
