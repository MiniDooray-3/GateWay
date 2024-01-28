package com.nhnacademy.edu.minidooray.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

@SpringBootTest
@AutoConfigureMockMvc
class SignupControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    void testSignupForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signupForm"));
    }

    @Test
    @DisplayName("회원 가입 성공")
    void testDoSignupSuccess() throws Exception {
        doNothing().when(accountService).signUp(any());

        mockMvc.perform(post("/signup")
                        .param("id", "test")
                        .param("email", "email@nhnacademy.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("회원 가입 - 유효성 검사 실패")
    void testDoSignupFailed() throws Exception {
        mockMvc.perform(post("/signup")
                        .param("id", "")
                        .param("email", "email")
                        .param("password", ""))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("error"))
                .andExpect(result ->
                        assertThat(result.getResolvedException()).isInstanceOf(ValidationFailedException.class)
                );
    }
}