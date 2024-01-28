package com.nhnacademy.edu.minidooray.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.minidooray.adapter.ProjectAdaptor;
import com.nhnacademy.edu.minidooray.domain.project.Project;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ProjectServiceImplTest {

    @Autowired
    ProjectService projectService;

    @MockBean
    ProjectAdaptor projectAdaptor;

    @Test
    void testCreateProject() {
        doNothing().when(projectAdaptor).createProject(any());

        projectService.createProject("project", "tester");

        verify(projectAdaptor, times(1)).createProject(any());
    }

    @Test
    void testGetProjects() {
        List<Project> projects = Arrays.asList(new Project(1L, "projct", "활성"));

        given(projectAdaptor.getProjects("tester")).willReturn(projects);

        List<Project> result = projectService.getProjects("tester");

        assertEquals(projects, result);

        verify(projectAdaptor, times(1)).getProjects(any());
    }

    @Test
    void testGetProject() {
        Project project = new Project(1L, "project", "활성");

        given(projectAdaptor.getProject(any())).willReturn(project);

        Project result = projectService.getProject(1L);

        assertEquals(project, result);

        verify(projectAdaptor, times(1)).getProject(any());
    }

    @Test
    void testModifyStatus() {
        doNothing().when(projectAdaptor).modifyProject(any());

        projectService.modifyStatus(1L, "휴면");

        verify(projectAdaptor, times(1)).modifyProject(any());
    }

}