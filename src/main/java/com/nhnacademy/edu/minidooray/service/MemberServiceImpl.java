package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.MemberAdaptor;
import com.nhnacademy.edu.minidooray.domain.member.Member;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberAdaptor memberAdaptor;

    public MemberServiceImpl(MemberAdaptor memberAdaptor) {
        this.memberAdaptor = memberAdaptor;
    }

    @Override
    public void createMember(Member member) {
        memberAdaptor.createMember(member);
    }

    @Override
    public List<Member> getMembers(Long projectId) {
        return memberAdaptor.getMembers(projectId);
    }

}