package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.milestone.GetMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.ModifyMilestone;
import com.nhnacademy.edu.minidooray.domain.milestone.RegisterMilestone;
import com.nhnacademy.edu.minidooray.property.TaskProperties;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
        restTemplate.exchange(taskProperties.getPort() + "/api/milestones",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Void>() {
                });

    }

    @Override
    public List<GetMilestone> getMilestones(Long projectId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetMilestone>> response =
                restTemplate.exchange(taskProperties.getPort() + "/api/milestones/" + projectId,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    @Override
    public void modifyMilestone(Long milestoneId, ModifyMilestone modifyMilestone) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<ModifyMilestone> entity = new HttpEntity<>(modifyMilestone, httpHeaders);
        restTemplate.exchange(taskProperties.getPort() + "/api/milestones/{milestoneId}",
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<Void>() {
                }, milestoneId);
    }

    @Override
    public void deleteMilestone(Long milestoneId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(taskProperties.getPort() + "/api/milestones/" + milestoneId,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<Void>() {
                });
    }
}
