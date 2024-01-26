package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.AccountAdaptor;
import com.nhnacademy.edu.minidooray.adapter.MemberAdaptor;
import com.nhnacademy.edu.minidooray.domain.UserRequest;
import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import com.nhnacademy.edu.minidooray.exception.UserAlreadyExistException;
import com.nhnacademy.edu.minidooray.exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberAdaptor memberAdaptor;
    private final AccountAdaptor accountAdaptor;

    public MemberServiceImpl(MemberAdaptor memberAdaptor, AccountAdaptor accountAdaptor) {
        this.memberAdaptor = memberAdaptor;
        this.accountAdaptor = accountAdaptor;
    }

    @Override
    public void createMember(RegisterMember member) {
        UserRequest existUser = accountAdaptor.isExistUser(member.getMemberId());

        if (!existUser.isHasAccount()) {
            throw new UserAlreadyExistException(member.getMemberId());
        }

        memberAdaptor.createMember(member);
    }

    @Override
    public List<GetMember> getMembers(Long projectId) {
        return memberAdaptor.getMembers(projectId);
    }

}