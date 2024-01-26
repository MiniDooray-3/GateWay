package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.service.MilestoneService;
import com.nhnacademy.edu.minidooray.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/tasks/register")
public class TaskRegisterController {

    private final TaskService taskService;
    private final MilestoneService milestoneService;


    public TaskRegisterController(TaskService taskService, MilestoneService milestoneService) {
        this.taskService = taskService;
        this.milestoneService = milestoneService;
    }

    @GetMapping
    public String taskRegisterForm(@SessionAttribute("projectId") Long projectId,
                                   Model model) {
        model.addAttribute("mileStones", milestoneService.getMilestones(projectId));

        return "taskRegisterForm";
    }

    @PostMapping
    public String registerTask(@SessionAttribute("projectId") Long projectId,
                               @ModelAttribute TaskRegisterRequest taskRegisterRequest) {
        taskService.registerTask(projectId, taskRegisterRequest);

        return "redirect:/projects/" + projectId;
    }
}
