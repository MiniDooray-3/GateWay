package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import java.util.List;

public interface MemberService {

    void createMember(RegisterMember member);

    List<GetMember> getMembers(Long projectId);

}