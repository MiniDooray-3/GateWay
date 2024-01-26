package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import com.nhnacademy.edu.minidooray.service.MemberService;
import com.nhnacademy.edu.minidooray.service.ProjectService;
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
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MemberController {

    private final MemberService memberService;



    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/list")
    public String getMembers(@SessionAttribute("project_id") Long projectId,
                             Model model) {

        List<GetMember> members = memberService.getMembers(projectId);
        model.addAttribute("members", members);

        return "memberList";
    }

    @GetMapping("/members/register")
    public String memberRegisterForm(@SessionAttribute("projectId") Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "memberRegisterForm";
    }

    @PostMapping("/members/register")
    public String registerMember(@SessionAttribute("projectId") Long projectId,
                               @ModelAttribute RegisterMember member) {
        memberService.createMember(member);

        return "redirect:/projects/" + projectId;
    }
}
