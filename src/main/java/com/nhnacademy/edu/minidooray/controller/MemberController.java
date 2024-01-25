package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.member.Member;
import com.nhnacademy.edu.minidooray.service.MemberService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/{project_id}")
    public String getMembers(@PathVariable("project_id") Long projectId, Model model) {

        List<Member> members = memberService.getMembers(projectId);
        model.addAttribute("members", members);

        return "memberList";
    }

    @GetMapping("/members/register")
    public String memberRegisterForm() {
        return "memberRegisterForm";
    }

    @PostMapping("/members/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMember(@RequestBody Member member) {
        memberService.createMember(member);

        return "redirect:/projects/{projectId}";
    }

}
