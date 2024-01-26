package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.RegisterMilestone;
import com.nhnacademy.edu.minidooray.domain.signup.SignupUser;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class MilestoneAdaptorImpl implements MilestoneAdaptor {

    private final RestTemplate restTemplate;

    private final TaskProperties taskProperties;

    public MilestoneAdaptorImpl(RestTemplate restTemplate, TaskProperties taskProperties) {
        this.restTemplate = restTemplate;
        this.taskProperties = taskProperties;
    }

    @Override
    public void createMilestone(RegisterMilestone registerMilestone) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<RegisterMilestone> entity = new HttpEntity<>(registerMilestone, httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange(taskProperties.getPort() + "/api/milestones",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });

        if (!HttpStatus.CREATED.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public List<GetMilestone> getMilestones(Long projectId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetMilestone>> response = restTemplate.exchange(taskProperties.getPort() + "/api/milestones/" + projectId,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });

        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }

        return response.getBody();
    }
}
