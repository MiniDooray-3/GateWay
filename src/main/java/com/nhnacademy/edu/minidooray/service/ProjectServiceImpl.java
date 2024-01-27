package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.ProjectAdaptor;
import com.nhnacademy.edu.minidooray.domain.project.Project;
import com.nhnacademy.edu.minidooray.domain.project.ProjectModify;
import com.nhnacademy.edu.minidooray.domain.project.ProjectRegister;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectAdaptor projectAdaptor;

    public ProjectServiceImpl(ProjectAdaptor projectAdaptor) {
        this.projectAdaptor = projectAdaptor;
    }

    @Override
    public void createProject(String projectName, String memberId) {
        projectAdaptor.createProject(new ProjectRegister(projectName, memberId));
    }

    @Override
    public List<Project> getProjects(String memberId) {
        return projectAdaptor.getProjects(memberId);
    }

    @Override
    public Project getProject(Long projectId) {
        return projectAdaptor.getProject(projectId);
    }

    @Override
    public void modifyStatus(Long projectId, String status) {
        projectAdaptor.modifyProject(new ProjectModify(projectId, status));
    }
}
