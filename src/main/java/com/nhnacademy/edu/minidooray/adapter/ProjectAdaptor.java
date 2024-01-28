package com.nhnacademy.edu.minidooray.adapter;


import com.nhnacademy.edu.minidooray.domain.project.ProjectModify;
import com.nhnacademy.edu.minidooray.domain.project.Project;
import com.nhnacademy.edu.minidooray.domain.project.ProjectRegister;
import java.util.List;

public interface ProjectAdaptor {

    void createProject(ProjectRegister projectRegister);

    void modifyProject(ProjectModify projectModify);

    List<Project> getProjects(String memberId);

    Project getProject(Long projectId);
}
