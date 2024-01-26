package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.project.Project;
import com.nhnacademy.edu.minidooray.domain.project.ProjectRegister;
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
public class ProjectAdaptorImpl implements ProjectAdaptor {

    private final RestTemplate restTemplate;
    private final TaskProperties taskProperties;

    public ProjectAdaptorImpl(RestTemplate restTemplate, TaskProperties taskProperties) {
        this.restTemplate = restTemplate;
        this.taskProperties = taskProperties;
    }

    @Override
    public void createProject(ProjectRegister projectRegister) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<ProjectRegister> entity = new HttpEntity<>(projectRegister, httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange(taskProperties.getPort() + "/api/projects",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });

        if (!HttpStatus.CREATED.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public List<Project> getProjects(String memberId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Project>> response = restTemplate.exchange(
                taskProperties.getPort() + "/api/projects/{memberId}/list",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }, memberId);

        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return response.getBody();
    }

    @Override
    public Project getProject(Long projectId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Project> response = restTemplate.exchange(taskProperties.getPort() + "/api/projects/{projectId}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }, projectId);

        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return response.getBody();
    }


}
