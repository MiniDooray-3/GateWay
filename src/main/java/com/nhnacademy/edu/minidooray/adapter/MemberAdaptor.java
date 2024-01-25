package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.member.Member;
import java.util.List;

public interface MemberAdaptor {

    void createMember(Member member);

    List<Member> getMembers(Long projectId);

}
