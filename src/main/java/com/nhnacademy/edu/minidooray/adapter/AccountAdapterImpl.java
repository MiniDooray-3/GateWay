package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.login.LoginUser;
import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;
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
import org.springframework.web.client.RestTemplate;

@Component
public class AccountAdapterImpl implements AccountAdapter {

    private final RestTemplate restTemplate;

    private final AccountProperties accountProperties;


    public AccountAdapterImpl(RestTemplate restTemplate, AccountProperties accountProperties) {
        this.restTemplate = restTemplate;
        this.accountProperties = accountProperties;
    }

    public boolean matches(LoginUser user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<LoginUser> entity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange(accountProperties.getPort() + "/api/accounts/login",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });

        return HttpStatus.OK.equals(response.getStatusCode());
    }

    @Override
    public boolean createUser(SignupUser signupUser) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<SignupUser> entity = new HttpEntity<>(signupUser, httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange(accountProperties.getPort() + "/api/signup",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });

        return HttpStatus.CREATED.equals(response.getStatusCode());
    }


}
