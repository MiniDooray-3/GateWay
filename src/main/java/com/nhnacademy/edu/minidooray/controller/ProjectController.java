package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.project.Project;
import com.nhnacademy.edu.minidooray.service.ProjectService;
import com.nhnacademy.edu.minidooray.service.TaskService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public String projectListForm(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("LOGIN_ID");

        if (Objects.nonNull(session.getAttribute("projectId"))) {
            session.removeAttribute("projectId");
        }

        model.addAttribute("projects", projectService.getProjects(userId));

        return "projectListForm";
    }

    @GetMapping("/{projectId}")
    public String projectViewForm(@PathVariable("projectId") Long projectId, Model model,
                                  HttpServletRequest request) {
        Project project = projectService.getProject(projectId);
        model.addAttribute("project", project);
        model.addAttribute("tasks", taskService.getTasks(projectId));

        request.getSession(true).setAttribute("projectId", projectId);

        return "projectViewForm";
    }

    @PostMapping("/modify")
    public String projectStatusModify(@SessionAttribute("projectId") Long projectId,
                                      @RequestParam("status") String status) {

        projectService.modifyStatus(projectId, status);

        return "redirect:/projects/" + projectId;
    }

}
