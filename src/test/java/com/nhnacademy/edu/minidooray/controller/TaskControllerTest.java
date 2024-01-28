package com.nhnacademy.edu.minidooray.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.domain.comment.GetComments;
import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import com.nhnacademy.edu.minidooray.domain.task.Task;
import com.nhnacademy.edu.minidooray.service.CommentService;
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

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @MockBean
    MilestoneService milestoneService;

    @MockBean
    TagService tagService;

    @MockBean
    CommentService commentService;

    @MockBean
    MemberService memberService;

    @BeforeEach
    void setup() {
        GetMember member = new GetMember("ADMIN", "tester");
        given(memberService.getMember(anyString(), any())).willReturn(member);
    }

    @Test
    void testTaskViewForm() throws Exception {
        Task task = new Task(1L, "testTitle", "test",
                new Task.Project(1L, "testName"),
                new Task.MileStone("TEST", 1L));
        List<GetTag> tags = Arrays.asList(new GetTag("testTagName", 1L));
        List<GetComments> comments = Arrays.asList(new GetComments(1L, "test", "tester"));

        given(taskService.getTask(1L)).willReturn(task);
        given(tagService.getTags(1L)).willReturn(tags);
        given(commentService.getComments(1L)).willReturn(comments);

        mockMvc.perform(get("/tasks/{task_id}", 1L)
                        .sessionAttr("LOGIN_ID", "tester")
                        .sessionAttr("projectId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("taskViewForm"))
                .andExpect(model().attribute("task", task));
    }


    @Test
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).removeTask(any());

        mockMvc.perform(get("/tasks/{task_id}/delete", 1L)
                        .sessionAttr("LOGIN_ID", "test")
                        .sessionAttr("projectId", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/projects/1"));
    }

    @Test
    void testTaskModifyForm() throws Exception {
        List<GetMilestone> milestones = Arrays.asList(new GetMilestone(1L, "개발"));
        List<GetTag> tags = Arrays.asList(new GetTag("testTagName", 1L));
        Task task = new Task(1L, "testTitle", "test",
                new Task.Project(1L, "testName"),
                new Task.MileStone("TEST", 1L));

        given(milestoneService.getMilestones(any())).willReturn(milestones);
        given(tagService.getTags(any())).willReturn(tags);
        given(taskService.getTask(any())).willReturn(task);

        mockMvc.perform(get("/tasks/{task_id}/modify", 1L)
                .sessionAttr("LOGIN_ID", "tester")
                .sessionAttr("projectId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("taskModifyForm"))
                .andExpect(model().attribute("task", task));
    }

    @Test
    void testModifyTask() throws Exception {
        doNothing().when(taskService).modifyTask(any(), any());

        mockMvc.perform(post("/tasks/{task_id}/modify", 1L)
                .sessionAttr("LOGIN_ID",  "tester")
                .sessionAttr("projectId", 1L)
                        .param("taskContent", "content")
                        .param("mileStoneId", "1")
                        .param("tagId", "1"))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(redirectedUrl("/tasks/1"));
    }

}