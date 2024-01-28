package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.UserRequest;
import com.nhnacademy.edu.minidooray.domain.login.LoginUser;
import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;
import com.nhnacademy.edu.minidooray.property.AccountProperties;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountAdaptorImpl implements AccountAdaptor {

    private final RestTemplate restTemplate;

    private final AccountProperties accountProperties;


    public AccountAdaptorImpl(RestTemplate restTemplate, AccountProperties accountProperties) {
        this.restTemplate = restTemplate;
        this.accountProperties = accountProperties;
    }

    public boolean matches(LoginUser user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        try {
            HttpEntity<LoginUser> entity = new HttpEntity<>(user, httpHeaders);
            restTemplate.exchange(accountProperties.getPort() + "/api/accounts/login",
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<Void>() {
                    });

            return true;
        } catch (HttpClientErrorException.Unauthorized e) {
            return false;
        }
    }

    @Override
    public void createUser(SignupUser signupUser) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<SignupUser> entity = new HttpEntity<>(signupUser, httpHeaders);
        restTemplate.exchange(accountProperties.getPort() + "/api/accounts/signup",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });

    }

    @Override
    public void deleteUser(String userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(accountProperties.getPort() + "/api/accounts/{userId}",
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<>() {
                }, userId);
    }

    @Override
    public UserRequest isExistUser(String userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<UserRequest> response =
                restTemplate.exchange(accountProperties.getPort() + "/api/accounts/{userId}",
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<>() {
                        }, userId);

        return response.getBody();
    }
}
