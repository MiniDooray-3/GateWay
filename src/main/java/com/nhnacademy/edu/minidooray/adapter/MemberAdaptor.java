package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import java.util.List;

public interface MemberAdaptor {

    void createMember(RegisterMember member);

    List<GetMember> getMembers(Long projectId);

}