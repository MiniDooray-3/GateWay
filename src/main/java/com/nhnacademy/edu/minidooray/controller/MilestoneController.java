package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.milestone.ModifyMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.RegisterMilestone;
import com.nhnacademy.edu.minidooray.exception.ValidationFailedException;
import com.nhnacademy.edu.minidooray.service.MilestoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PostMapping("/register")
    public String doMilestoneRegister(@Validated @ModelAttribute RegisterMilestone registerMilestone,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        milestoneService.createMilestone(registerMilestone);

        return "redirect:/projects/" + registerMilestone.getProjectId();
    }

    @GetMapping("/{projectId}/list")
    public String getMilestones(@PathVariable("projectId") Long projectId, Model model) {
        model.addAttribute("milestones", milestoneService.getMilestones(projectId));

        return "milestoneListForm";
    }

    @GetMapping("/{milestoneId}/modify")
    public String modifyMilestone(@PathVariable("milestoneId") Long milestoneId,
                                  @RequestParam("milestoneStatus") String milestoneStatus,
                                  Model model) {
        model.addAttribute("milestoneId", milestoneId);
        model.addAttribute("milestoneStatus", milestoneStatus);

        return "milestoneModifyForm";
    }

    @PostMapping("/milestones/{milestoneId}/modify")
    public String doModifyMilestone(@Validated @ModelAttribute ModifyMilestone modifyMilestone,
                                  @SessionAttribute("projectId") Long projectId) {
        milestoneService.modifyMilestone(modifyMilestone);

        return "redirect:/milestones/" + projectId;
    }

    @GetMapping("/milestones/delete/{milestoneId}")
    public String doMilestoneDelete(@PathVariable("milestoneId") Long milestoneId,
                                    @SessionAttribute("projectId") Long projectId) {

        milestoneService.deleteMilestone(milestoneId);

        return "redirect:/milestones/" + projectId;
    }
}