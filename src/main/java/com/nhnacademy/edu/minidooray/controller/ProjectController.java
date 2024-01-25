package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.service.ProjectService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String projectForm() {
        return "projectForm";
    }

    @GetMapping("/list")
    public String projectListForm(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        String userId = (String) session.getAttribute("LOGIN_ID");

        model.addAttribute("projects", projectService.getProjects(userId));

        return "projectListForm";
    }

    @GetMapping("/{projectId}")
    public String projectViewForm(@PathVariable("projectId") Long projectId, Model model) {
        model.addAttribute("project", projectService.getProject(projectId));

        return "projectViewForm";
    }

}
