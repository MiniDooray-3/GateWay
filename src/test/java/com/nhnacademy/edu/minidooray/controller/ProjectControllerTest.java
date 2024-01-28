package com.nhnacademy.edu.minidooray.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.project.Project;
import com.nhnacademy.edu.minidooray.domain.task.TaskViewRequest;
import com.nhnacademy.edu.minidooray.interceptor.ProjectMemberCheckInterceptor;
import com.nhnacademy.edu.minidooray.interceptor.ProjectMemberCheckInterceptorBefore;
import com.nhnacademy.edu.minidooray.service.MemberService;
import com.nhnacademy.edu.minidooray.service.ProjectService;
import com.nhnacademy.edu.minidooray.service.TaskService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest()
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProjectService projectService;

    @MockBean
    TaskService taskService;

    @MockBean
    MemberService memberService;

    @BeforeEach
    void setup() {
        GetMember member = new GetMember("ADMIN", "tester");
        given(memberService.getMember(anyString(), any())).willReturn(member);
    }

    @Test
    void testProjectListForm() throws Exception {
        List<Project> projects = Arrays.stream(new Project[] {
                new Project(1L, "testName", "활성")
        }).collect(Collectors.toList());

        given(projectService.getProjects(any())).willReturn(projects);

        mockMvc.perform(get("/projects/list")
                        .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().isOk())
                .andExpect(view().name("projectListForm"))
                .andExpect(model().attribute("projects", projects));

        verify(projectService, times(1)).getProjects(any());
    }

    @Test
    @DisplayName("프로젝트 리스트 폼 - 이전에 조회한 프로젝트 기록 삭제")
    void testProjectListFormSessionRemove() throws Exception {

        List<Project> projects = Arrays.stream(new Project[] {
                new Project(1L, "testName", "활성")
        }).collect(Collectors.toList());

        given(projectService.getProjects(any())).willReturn(projects);

        mockMvc.perform(get("/projects/list")
                        .sessionAttr("LOGIN_ID", "tester")
                        .sessionAttr("projectId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("projectListForm"))
                .andExpect(model().attribute("projects", projects))
                .andExpect(request().sessionAttributeDoesNotExist("projectId"));

        verify(projectService, times(1)).getProjects(any());
    }

    @Test
    void testProjectViewForm() throws Exception {
        Project project = new Project(1L, "testName", "활성");
        List<TaskViewRequest> tasks = Arrays.stream(new TaskViewRequest[] {
                new TaskViewRequest(1L, "testTitle",
                        new TaskViewRequest.MileStone("testMileStone", 1L))
        }).collect(Collectors.toList());

        given(projectService.getProject(1L)).willReturn(project);
        given(taskService.getTasks(1L)).willReturn(tasks);

        mockMvc.perform(get("/projects/{projectId}", 1L)
                        .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().isOk())
                .andExpect(view().name("projectViewForm"))
                .andExpect(model().attribute("project", project))
                .andExpect(model().attribute("tasks", tasks));
    }

    @Test
    void testProjectStatusModify() throws Exception {
        doNothing().when(projectService).modifyStatus(any(), any());

        mockMvc.perform(post("/projects/modify")
                .sessionAttr("LOGIN_ID", "tester")
                .sessionAttr("projectId", 1L)
                .param("status", "휴면"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/projects/1"));
    }


}