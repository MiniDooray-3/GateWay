package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.member.Member;
import java.util.List;

public interface MemberService {

    void createMember(Member member);

    List<Member> getMembers(Long projectId);

}