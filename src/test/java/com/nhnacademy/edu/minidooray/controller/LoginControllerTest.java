package com.nhnacademy.edu.minidooray.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.exception.ValidationFailedException;
import com.nhnacademy.edu.minidooray.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest()
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    @DisplayName("login view 테스트")
    void testLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"));

    }

    @Test
    @DisplayName("로그인 시도 - 성공")
    void testDoLoginSuccess() throws Exception {
        given(accountService.matches(anyString(), anyString())).willReturn(true);

        mockMvc.perform(post("/login")
                        .param("userId", "tester")
                        .param("userPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(request().sessionAttribute("LOGIN_ID", "tester"));
    }

    @Test
    @DisplayName("로그인 시도 - 실패")
    void testDoLoginFail() throws Exception {
        given(accountService.matches(anyString(), anyString())).willReturn(false);

        mockMvc.perform(post("/login")
                        .param("userId", "tester")
                        .param("userPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    @DisplayName("로그인 시도 - 유효성 검사 실패")
    void testDoLoginValidFailed() throws Exception {
        given(accountService.matches(anyString(), anyString())).willThrow(ValidationFailedException.class);

        mockMvc.perform(post("/login")
                        .param("userId", "tester")
                        .param("userPassword", "password"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("error"));
    }

}