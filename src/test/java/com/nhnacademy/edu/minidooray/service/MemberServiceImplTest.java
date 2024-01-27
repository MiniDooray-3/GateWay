package com.nhnacademy.edu.minidooray.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.minidooray.adapter.MemberAdaptor;
import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @MockBean
    MemberAdaptor memberAdaptor;

    @Test
    @DisplayName("멤버 등록 - 성공")
    void testCreateMember() {
        RegisterMember member = new RegisterMember("vel13", 1L, "MEMBER");

        memberService.createMember(member);

        verify(memberAdaptor, times(1)).createMember(member);
    }

    @Test
    @DisplayName("멤버 전부 조회 - 성공")
    void testGetMembers() {
        Long projectId = 1L;
        List<GetMember> members = List.of(
                new GetMember("ADMIN", "straw151"),
                new GetMember("MEMBER", "greet11"));

        when(memberAdaptor.getMembers(projectId)).thenReturn(members);

        List<GetMember> result = memberService.getMembers(projectId);

        assertThat(result).isEqualTo(members);
    }

    @Test
    @DisplayName("멤버 1명 조회 - 성공")
    void testGetMember() {
        Long projectId = 1L;
        String userId = "denny12";
        GetMember member = new GetMember("ADMIN", "denny12");

        when(memberAdaptor.getMember(userId, projectId)).thenReturn(member);

        GetMember result = memberService.getMember(userId, projectId);

        assertThat(result).isEqualTo(member);
    }
}