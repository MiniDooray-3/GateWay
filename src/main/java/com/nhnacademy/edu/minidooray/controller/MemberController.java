package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import com.nhnacademy.edu.minidooray.service.MemberService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MemberController {

    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/{project_id}")
    @ResponseStatus(HttpStatus.OK)
    public String getMembers(@PathVariable("project_id") Long projectId, Model model) {

        List<GetMember> members = memberService.getMembers(projectId);
        model.addAttribute("members", members);
        model.addAttribute("projectName", projectId);
        // TODO : projectName : projectService를 완성하면
        //  projectService.getProject(projectId).getName() 으로 받아오기

        return "memberList";
    }

    @GetMapping("/members/register/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public String memberRegisterForm(@PathVariable("projectId") Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "memberRegisterForm";
    }

    @PostMapping("/members/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMember(@RequestParam("projectId") Long projectId,
                               @ModelAttribute RegisterMember member) {
        memberService.createMember(member);

        return "redirect:/projects/" + projectId;
    }
}