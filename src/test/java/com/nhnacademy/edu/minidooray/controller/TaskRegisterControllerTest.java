package com.nhnacademy.edu.minidooray.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import com.nhnacademy.edu.minidooray.service.MemberService;
import com.nhnacademy.edu.minidooray.service.MilestoneService;
import com.nhnacademy.edu.minidooray.service.TagService;
import com.nhnacademy.edu.minidooray.service.TaskService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class TaskRegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @MockBean
    MilestoneService milestoneService;

    @MockBean
    TagService tagService;

    @MockBean
    MemberService memberService;

    @BeforeEach
    void setup() {
        GetMember member = new GetMember("ADMIN", "tester");
        given(memberService.getMember(anyString(), any())).willReturn(member);
    }

    @Test
    void testTaskRegisterForm() throws Exception {
        List<GetMilestone> milestones = Arrays.asList(new GetMilestone(1L, "개발"));
        List<GetTag> tags = Arrays.asList(new GetTag("tag", 1L));

        given(milestoneService.getMilestones(any())).willReturn(milestones);
        given(tagService.getTags(any())).willReturn(tags);

        mockMvc.perform(get("/tasks/register")
                .sessionAttr("LOGIN_ID", "tester")
                .sessionAttr("projectId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("taskRegisterForm"))
                .andExpect(model().attributeExists("mileStones"))
                .andExpect(model().attributeExists("tags"));
    }

    @Test
    void testRegisterTask() throws Exception {
        doNothing().when(taskService).registerTask(any(), any());

        mockMvc.perform(post("/tasks/register")
                .sessionAttr("LOGIN_ID", "tester")
                .sessionAttr("projectId", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects/1"));
    }


}