package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.project.Project;
import java.util.List;

public interface ProjectService {

    void createProject(String projectName, String memberId);

    List<Project> getProjects(String memberId);

    Project getProject(Long projectId);

    void modifyStatus(Long projectId, String status);
}
