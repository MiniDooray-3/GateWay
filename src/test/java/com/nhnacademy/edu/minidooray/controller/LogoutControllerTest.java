package com.nhnacademy.edu.minidooray.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest()
@AutoConfigureMockMvc
class LogoutControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("로그아웃 테스트")
    void testLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/logout")
                .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(request().sessionAttributeDoesNotExist("LOGIN_ID"));
    }

}