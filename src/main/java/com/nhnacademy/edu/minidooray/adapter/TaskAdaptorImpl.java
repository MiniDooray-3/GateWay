package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.task.TaskRegister;
import com.nhnacademy.edu.minidooray.property.TaskProperties;
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
public class TaskAdaptorImpl implements TaskAdaptor {

    private final RestTemplate restTemplate;

    private final TaskProperties taskProperties;


    public TaskAdaptorImpl(RestTemplate restTemplate, TaskProperties taskProperties) {
        this.restTemplate = restTemplate;
        this.taskProperties = taskProperties;
    }

    @Override
    public void createTask(TaskRegister taskRegister) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TaskRegister> entity = new HttpEntity<>(taskRegister, httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange(taskProperties.getPort() + "/api/tasks",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });

        if (!HttpStatus.CREATED.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }
    }

    @Override
    public void deleteTask(Long taskId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Long> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange(taskProperties.getPort() + "/api/tasks/{taskId}",
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<>() {
                }, taskId);

        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

}
