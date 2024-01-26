package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.member.RegisterMember;
import com.nhnacademy.edu.minidooray.property.TaskProperties;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

@Component
public class MemberAdaptorImpl implements MemberAdaptor {

    private final RestTemplate restTemplate;


    private final TaskProperties taskProperties;

    public MemberAdaptorImpl(RestTemplate restTemplate, TaskProperties taskProperties) {
        this.restTemplate = restTemplate;
        this.taskProperties = taskProperties;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void createMember(RegisterMember member) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<RegisterMember> requestEntity = new HttpEntity<>(member, httpHeaders);
        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/members/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (!HttpStatus.CREATED.equals(exchange.getStatusCode())) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<GetMember> getMembers(Long projectId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetMember>> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/members/{projectId}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, projectId);

        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }

}