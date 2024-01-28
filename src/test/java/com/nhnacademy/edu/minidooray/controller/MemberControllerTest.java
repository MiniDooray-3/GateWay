package com.nhnacademy.edu.minidooray.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import com.nhnacademy.edu.minidooray.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @BeforeEach
    void setup() {
        GetMember member = new GetMember("ADMIN", "tester");
        given(memberService.getMember(anyString(), any())).willReturn(member);
    }

    @Test
    @DisplayName("멤버 전부 조회 - 성공")
    void testGetMembers() throws Exception {
        Long projectId = 1L;
        List<GetMember> expectedMembers = List.of(
                new GetMember("MEMBER", "lux"),
                new GetMember("ADMIN", "blang"));

        when(memberService.getMembers(projectId)).thenReturn(expectedMembers);

        mockMvc.perform(get("/members/list")
                        .sessionAttr("projectId", projectId)
                        .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("members"))
                .andExpect(model().attribute("members", expectedMembers))
                .andExpect(view().name("memberList"));

        verify(memberService, times(1)).getMembers(projectId);
    }

    @Test
    @DisplayName("멤버 전부 조회 - 성공 : 멤버 존재 X")
    void testGetMembers_WhenNoMembersExist_ShouldReturnEmptyList() throws Exception {
        Long projectId = 1L;
        List<GetMember> emptyMemberList = new ArrayList<>();

        when(memberService.getMembers(projectId)).thenReturn(emptyMemberList);

        mockMvc.perform(get("/members/list")
                        .sessionAttr("projectId", projectId)
                        .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("members"))
                .andExpect(model().attribute("members", emptyMemberList))
                .andExpect(view().name("memberList"));

        verify(memberService, times(1)).getMembers(projectId);
    }

    @Test
    @DisplayName("멤버 등록 페이지 - 성공")
    void testMemberRegisterForm() throws Exception {
        Long projectId = 1L;

        mockMvc.perform(get("/members/register")
                        .sessionAttr("projectId", projectId)
                        .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("projectId"))
                .andExpect(model().attribute("projectId", projectId))
                .andExpect(view().name("memberRegisterForm"));
    }

    @Test
    @DisplayName("멤버 등록 - 성공")
    void testRegisterMember() throws Exception {
        Long projectId = 1L;
        RegisterMember member = new RegisterMember("soulDel", 1L, "ADMIN");

        mockMvc.perform(post("/members/register")
                        .param("memberId", member.getMemberId())
                        .param("projectId", member.getProjectId().toString())
                        .param("memberRole", member.getMemberRole())
                        .sessionAttr("projectId", projectId)
                        .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects/" + projectId));

        verify(memberService).createMember(any());
    }

}