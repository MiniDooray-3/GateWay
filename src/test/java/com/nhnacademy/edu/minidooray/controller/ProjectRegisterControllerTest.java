package com.nhnacademy.edu.minidooray.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectRegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProjectService projectService;

    @Test
    void testProjectRegisterForm() throws Exception {
        mockMvc.perform(get("/projects/register")
                        .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().isOk())
                .andExpect(view().name("projectRegister"));
    }

    @Test
    void tsetDoProjectRegister() throws Exception {
        doNothing().when(projectService).createProject(anyString(), anyString());

        mockMvc.perform(post("/projects/register")
                        .sessionAttr("LOGIN_ID", "tester")
                        .param("projectName", "testName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/projects/list"));
    }


}