package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.member.Member;
import com.nhnacademy.edu.minidooray.property.AccountProperties;
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


    private final AccountProperties accountProperties;

    public MemberAdaptorImpl(RestTemplate restTemplate, AccountProperties accountProperties) {
        this.restTemplate = restTemplate;
        this.accountProperties = accountProperties;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void createMember(Member member) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Member> requestEntity = new HttpEntity<>(member, httpHeaders);
        ResponseEntity<Void> exchange = restTemplate.exchange(
                accountProperties.getPort() + "/members/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Void>() {
                });

        if (HttpStatus.CREATED.equals(exchange.getStatusCode())) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Member> getMembers(Long projectId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Member>> exchange = restTemplate.exchange(
                accountProperties.getPort() + "/members/" + projectId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new RuntimeException();
        }
        return exchange.getBody();
    }

}
