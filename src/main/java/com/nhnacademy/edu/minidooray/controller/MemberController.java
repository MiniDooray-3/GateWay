package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import com.nhnacademy.edu.minidooray.exception.ValidationFailedException;
import com.nhnacademy.edu.minidooray.service.MemberService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MemberController {

    private final MemberService memberService;



    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/list")
    public String getMembers(@SessionAttribute("projectId") Long projectId,
                             Model model) {

        List<GetMember> members = memberService.getMembers(projectId);
        model.addAttribute("members", members);

        return "memberList";
    }

    @GetMapping("/members/register")
    public String memberRegisterForm(@SessionAttribute("projectId") Long projectId,
                                     Model model) {
        model.addAttribute("projectId", projectId);
        return "memberRegisterForm";
    }

    @PostMapping("/members/register")
    public String registerMember(@SessionAttribute("projectId") Long projectId,
                                 @Valid @ModelAttribute RegisterMember member,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        memberService.createMember(member);

        return "redirect:/projects/" + projectId;
    }
}
