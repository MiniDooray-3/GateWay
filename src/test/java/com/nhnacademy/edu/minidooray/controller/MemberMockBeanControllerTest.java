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
import java.util.ArrayList;
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
    public void testGetMembers() throws Exception {
        Long projectId = 1L;
        List<GetMember> members = new ArrayList<>();

        when(adaptor.getMembers(projectId)).thenReturn(members);

        mockMvc.perform(get("/members/{project_id}", projectId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("members"))
                .andExpect(MockMvcResultMatchers.model().attribute("projectName", projectId))
                .andExpect(MockMvcResultMatchers.view().name("memberList"));
    }

    @Test
    @DisplayName("멤버 등록 페이지")
    public void testMemberRegisterForm() throws Exception {
        Long projectId = 1L;

        mockMvc.perform(get("/members/register/{projectId}", projectId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("projectId", projectId))
                .andExpect(MockMvcResultMatchers.view().name("memberRegisterForm"));
    }

    @Test
    @DisplayName("멤버 등록 ")
    public void testRegisterMember() throws Exception {
        Long projectId = 1L;
        RegisterMember member = new RegisterMember("hello", 1L, "ADMIN");
        // member 객체에 테스트용 데이터 설정

        mockMvc.perform(post("/members/register")
                        .param("projectId", String.valueOf(projectId))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("member", member))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/projects/" + projectId));
    }
}