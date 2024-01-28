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

    private static final String REDIRECT_URL = "redirect:/milestones/list";

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

        return REDIRECT_URL;
    }

    @GetMapping("/list")
    public String getMilestones(@SessionAttribute("projectId") Long projectId, Model model) {
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

    @PostMapping("/{milestoneId}/modify")
    public String doModifyMilestone(@PathVariable Long milestoneId,
                                    @Validated @ModelAttribute ModifyMilestone modifyMilestone,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        milestoneService.modifyMilestone(milestoneId, modifyMilestone);

        return REDIRECT_URL;
    }

    @GetMapping("/{milestoneId}/delete")
    public String doMilestoneDelete(@PathVariable("milestoneId") Long milestoneId) {

        milestoneService.deleteMilestone(milestoneId);

        return REDIRECT_URL;
    }
}