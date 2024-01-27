package com.nhnacademy.edu.minidooray.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import com.nhnacademy.edu.minidooray.property.TaskProperties;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class MemberAdaptorImplTest {

    @Autowired
    MemberAdaptor memberAdaptor;

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    TaskProperties taskProperties;


    @Test
    void createMember_ValidMember_Success() {
        RegisterMember member = new RegisterMember("dingo12", 1L, "MEMBER");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<RegisterMember> requestEntity = new HttpEntity<>(member, httpHeaders);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);

        doNothing().when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.POST),
                eq(requestEntity),
                eq(ParameterizedTypeReference.class)
        ));

        memberAdaptor.createMember(member);

        verify(restTemplate).exchange(
                any(String.class),
                eq(HttpMethod.POST),
                eq(requestEntity),
                eq(ParameterizedTypeReference.class)
        );
    }

    @Test
    void getMembers_ValidProjectId_Success() {
        Long projectId = 1L;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetMember>> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                eq(requestEntity),
                eq(new ParameterizedTypeReference<List<GetMember>>() {}),
                eq(projectId)
        )).thenReturn(responseEntity);

        List<GetMember> members = memberAdaptor.getMembers(projectId);

        verify(restTemplate).exchange(
                any(String.class),
                eq(HttpMethod.GET),
                eq(requestEntity),
                eq(new ParameterizedTypeReference<List<GetMember>>() {}),
                eq(projectId)
        );
        assertThat(responseEntity.getBody()).isEqualTo(members);
    }

    @Test
    void getMember_ValidUserIdAndProjectId_Success() {
        String userId = "lombok17";
        Long projectId = 1L;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<GetMember> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                eq(requestEntity),
                eq(new ParameterizedTypeReference<GetMember>() {}),
                eq(userId),
                eq(projectId)
        )).thenReturn(responseEntity);

        GetMember member = memberAdaptor.getMember(userId, projectId);

        verify(restTemplate).exchange(
                any(String.class),
                eq(HttpMethod.GET),
                eq(requestEntity),
                eq(new ParameterizedTypeReference<GetMember>() {}),
                eq(userId),
                eq(projectId)
        );
        assertThat(responseEntity.getBody()).isEqualTo(member);
    }
}