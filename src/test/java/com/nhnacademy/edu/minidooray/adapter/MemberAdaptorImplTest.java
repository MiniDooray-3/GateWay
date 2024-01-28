package com.nhnacademy.edu.minidooray.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
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
    void testCreateMember_ValidMember_Success() {
        RegisterMember member = new RegisterMember("dingo12", 1L, "MEMBER");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<RegisterMember> requestEntity = new HttpEntity<>(member, httpHeaders);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);

        when(restTemplate.exchange(
                taskProperties.getPort() + "/api/members/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Void>() {}
        )).thenReturn(responseEntity);

        memberAdaptor.createMember(member);

        verify(restTemplate).exchange(
                taskProperties.getPort() + "/api/members/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Void>() {}
        );
    }

    @Test
    void testGetMembers_ValidProjectId_Success() {
        Long projectId = 1L;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        List<GetMember> expectedMembers = List.of(
                new GetMember("ADMIN", "gang1"),
                new GetMember("MEMBER", "nan1"),
                new GetMember("MEMBER", "kong1")
        );

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetMember>> responseEntity = new ResponseEntity<>(expectedMembers, HttpStatus.OK);

        when(restTemplate.exchange(
                taskProperties.getPort() + "/api/members/{projectId}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<GetMember>>() {},
                projectId
        )).thenReturn(responseEntity);

        List<GetMember> actualMembers = memberAdaptor.getMembers(projectId);

        verify(restTemplate).exchange(
                taskProperties.getPort() + "/api/members/{projectId}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<GetMember>>() {},
                projectId
        );
        assertThat(responseEntity.getBody()).isEqualTo(actualMembers);
    }

    @Test
    void testGetMember_ValidUserIdAndProjectId_Success() {
        String userId = "lombok17";
        Long projectId = 1L;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        GetMember expectedMember = new GetMember("ADMIN", "gang1");

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<GetMember> responseEntity = new ResponseEntity<>(expectedMember, HttpStatus.OK);

        when(restTemplate.exchange(
                taskProperties.getPort() + "/api/members/{member_id}/{project_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<GetMember>() {},
                userId,
                projectId
        )).thenReturn(responseEntity);

        GetMember actualMember = memberAdaptor.getMember(userId, projectId);

        verify(restTemplate).exchange(
                taskProperties.getPort() + "/api/members/{member_id}/{project_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<GetMember>() {},
                userId,
                projectId
        );
        assertThat(responseEntity.getBody()).isEqualTo(actualMember);
    }
}