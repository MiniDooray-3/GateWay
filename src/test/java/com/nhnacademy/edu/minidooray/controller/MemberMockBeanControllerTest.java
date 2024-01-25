package com.nhnacademy.edu.minidooray.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.adapter.MemberAdaptor;
import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MemberMockBeanControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberAdaptor adaptor;

    @Test
    @DisplayName("멤버 전부 조회")
    void testGetMembers() throws Exception {
        List<GetMember> mockMembers = Arrays.asList(
                new GetMember("ADMIN", "hello"),
                new GetMember("MEMBER", "dell")
        );
        when(adaptor.getMembers(1L)).thenReturn(mockMembers);

        mockMvc.perform(get("/members/{project_id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("memberList"))
                .andExpect(model().attributeExists("members"))
                .andExpect(model().attributeExists("projectName"))
                .andExpect(jsonPath("$[0].memberId", Matchers.equalTo("hello")));
    }

    @Test
    void testMemberRegisterForm() throws Exception {
        mockMvc.perform(get("/members/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("memberRegisterForm"));
    }

    @Test
    void testCreateMember() throws Exception {
        mockMvc.perform(post("/members/register"))
                .andExpect(status().isCreated())
                .andExpect(view().name("redirect:/projects/"));
    }
}