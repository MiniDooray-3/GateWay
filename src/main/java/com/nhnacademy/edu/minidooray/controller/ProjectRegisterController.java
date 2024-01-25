package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.service.ProjectService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/projects/register")
public class ProjectRegisterController {

    private final ProjectService projectService;


    public ProjectRegisterController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String projectRegisterForm() {
        return "projectRegister";
    }

    @PostMapping
    public String doProjectRegister(@RequestParam("projectName") String projectName,
                                    HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("LOGIN_ID"))) {
            String userId = (String) session.getAttribute("LOGIN_ID");

            projectService.createProject(projectName, userId);
        }

        return "redirect:/projects/list";
    }
}
