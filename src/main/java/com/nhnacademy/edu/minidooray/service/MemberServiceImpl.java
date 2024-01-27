package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.MemberAdaptor;
import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberAdaptor memberAdaptor;

    public MemberServiceImpl(MemberAdaptor memberAdaptor) {
        this.memberAdaptor = memberAdaptor;
    }

    @Override
    public void createMember(RegisterMember member) {
        memberAdaptor.createMember(member);
    }

    @Override
    public List<GetMember> getMembers(Long projectId) {
        return memberAdaptor.getMembers(projectId);
    }

    @Override
    public GetMember getMember(String userId, Long projectId) {
        return memberAdaptor.getMember(userId, projectId);
    }

}
