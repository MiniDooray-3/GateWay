package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.task.TaskModifyRequest;
import com.nhnacademy.edu.minidooray.service.CommentService;
import com.nhnacademy.edu.minidooray.service.MilestoneService;
import com.nhnacademy.edu.minidooray.service.TagService;
import com.nhnacademy.edu.minidooray.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final MilestoneService milestoneService;
    private final TagService tagService;
    private final CommentService commentService;

    public TaskController(TaskService taskService, MilestoneService milestoneService, TagService tagService,
                          CommentService commentService) {
        this.taskService = taskService;
        this.milestoneService = milestoneService;
        this.tagService = tagService;
        this.commentService = commentService;
    }

    @GetMapping("/tasks/{task_id}")
    public String taskViewForm(@PathVariable("task_id") Long taskId,
                               @SessionAttribute("LOGIN_ID") String loginId,
                               Model model) {

        model.addAttribute("task", taskService.getTask(taskId));
        model.addAttribute("tags", tagService.getTagsByTaskId(taskId));
        model.addAttribute("comments", commentService.getComments(taskId));
        model.addAttribute("loginId", loginId);

        return "taskViewForm";
    }

    @GetMapping("/tasks/{task_id}/delete")
    public String deleteTask(@PathVariable("task_id") Long taskId,
                             @SessionAttribute("projectId") Long projectId) {

        taskService.removeTask(taskId);

        return "redirect:/projects/" + projectId;
    }

    @GetMapping("/tasks/{task_id}/modify")
    public String taskModifyForm(@PathVariable("task_id") Long taskId,
                                 @SessionAttribute("projectId") Long projectId,
                                 Model model) {
        model.addAttribute("mileStones", milestoneService.getMilestones(projectId));
        model.addAttribute("tags", tagService.getTags(projectId));
        model.addAttribute("task", taskService.getTask(taskId));

        return "taskModifyForm";
    }

    @PostMapping("/tasks/{task_id}/modify")
    public String modifyTask(@ModelAttribute TaskModifyRequest taskModifyRequest,
                             @PathVariable("task_id") Long taskId) {
        taskService.modifyTask(taskId, taskModifyRequest);

        return "redirect:/tasks/" + taskId;
    }
}
