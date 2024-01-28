package com.nhnacademy.edu.minidooray.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.service.MemberService;
import com.nhnacademy.edu.minidooray.service.MilestoneService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class MilestoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MilestoneService milestoneService;

    @MockBean
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        GetMember getMember = new GetMember("admin", "test");
        given(memberService.getMember(any(), any())).willReturn(getMember);
    }

    @Test
    @DisplayName("마일스톤 등록 성공")
    void testDoMilestoneRegister() throws Exception {
        String mileStoneStatus = "개발";

        mockMvc.perform(post("/milestones/register")
                        .sessionAttr("LOGIN_ID", "test")
                        .sessionAttr("projectId", 1L)
                        .param("milestoneStatus", mileStoneStatus)
                        .param("projectId", "18")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/milestones/list"));
    }

    @Test
    @DisplayName("유효성 검사로 인한 마일스톤 등록 실패")
    void testDoMilestoneRegisterByValidationFail() throws Exception {
        String mileStoneStatus = "";

        mockMvc.perform(post("/milestones/register")
                        .sessionAttr("LOGIN_ID", "test")
                        .sessionAttr("projectId", 1L)
                        .param("milestoneStatus", mileStoneStatus)
                        .param("projectId", "18")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("ObjectName=")))
                .andExpect(content().string(containsString(", Message=")))
                .andExpect(content().string(containsString(", code=")));
    }


    @Test
    @DisplayName("마일스톤 목록 조회 페이지 이동")
    void testGetMilestones() throws Exception {
        given(milestoneService.getMilestones(any(Long.class))).willReturn(List.of(new GetMilestone(1L, "개발")));

        mockMvc.perform(get("/milestones/list")
                        .sessionAttr("LOGIN_ID", "test")
                        .sessionAttr("projectId", 1L))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("milestones"))
                .andExpect(view().name("milestoneListForm"));
    }

    @Test
    @DisplayName("마일스톤 수정 페이지 이동")
    void testModifyMilestone() throws Exception {
        mockMvc.perform(get("/milestones/{milestoneId}/modify", 1L)
                        .sessionAttr("LOGIN_ID", "test")
                        .sessionAttr("projectId", 1L)
                        .param("milestoneStatus", "개발"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("milestoneId"))
                .andExpect(model().attributeExists("milestoneStatus"))
                .andExpect(view().name("milestoneModifyForm"));
    }

    @Test
    @DisplayName("마일스톤 수정 성공")
    void testDoModifyMilestoneSuccess() throws Exception {
        String milestoneStatus = "개발";

        mockMvc.perform(post("/milestones/{milestoneId}/modify", 1L)
                        .sessionAttr("LOGIN_ID", "test")
                        .sessionAttr("projectId", 1L)
                        .param("mileStoneStatus", milestoneStatus))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/milestones/list"));
    }

    @Test
    @DisplayName("유효성 검사로 인한 마일스톤 수정하기 실패")
    void testDoModifyMilestoneFailByValidationFail() throws Exception {
        String milestoneStatus = "";

        mockMvc.perform(post("/milestones/{milestoneId}/modify", 1L)
                        .sessionAttr("LOGIN_ID", "test")
                        .sessionAttr("projectId", 1L)
                        .param("mileStoneStatus", milestoneStatus))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("ObjectName=")))
                .andExpect(content().string(containsString(", Message=")))
                .andExpect(content().string(containsString(", code=")));
    }

    @Test
    @DisplayName("마일스톤 삭제")
    void doMilestoneDelete() throws Exception {
        mockMvc.perform(get("/milestones/{milestoneId}/delete", 1L)
                        .sessionAttr("LOGIN_ID", "test")
                        .sessionAttr("projectId", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/milestones/list"));
    }
}