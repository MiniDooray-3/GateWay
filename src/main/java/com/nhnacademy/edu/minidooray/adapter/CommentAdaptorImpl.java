package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.domain.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.domain.comment.CommentRegister;
import com.nhnacademy.edu.minidooray.domain.comment.GetComments;
import com.nhnacademy.edu.minidooray.domain.task.TaskOnlyId;
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
public class CommentAdaptorImpl implements CommentAdaptor{

    private final RestTemplate restTemplatet;
    private final TaskProperties taskProperties;

    public CommentAdaptorImpl(RestTemplate restTemplatet, TaskProperties taskProperties) {
        this.restTemplatet = restTemplatet;
        this.taskProperties = taskProperties;
    }


    @Override
    public void createComment(CommentRegister commentRegister) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<CommentRegister> entity = new HttpEntity<>(commentRegister, httpHeaders);
        ResponseEntity<Void> response = restTemplatet.exchange(taskProperties.getPort() + "/api/comments",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });

        if (!HttpStatus.CREATED.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public List<GetComments> getComments(Long taskId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetComments>> response = restTemplatet.exchange(taskProperties.getPort() + "/api/comments/{task_id}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }, taskId);

        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return response.getBody();
    }

    @Override
    public CommentIdAndContent getComment(Long commentId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<CommentIdAndContent> response = restTemplatet.exchange(taskProperties.getPort() + "/api/comment/{comment_id}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }, commentId);

        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return response.getBody();
    }

    @Override
    public TaskOnlyId modifyComment(Long commentId, CommentModifyRequest commentModifyRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<CommentModifyRequest> entity = new HttpEntity<>(commentModifyRequest, httpHeaders);
        ResponseEntity<TaskOnlyId> response = restTemplatet.exchange(taskProperties.getPort() + "/api/comments/{comment_id}",
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<>() {
                }, commentId);

        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        return response.getBody();
    }

    @Override
    public TaskOnlyId deleteComment(Long commentId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<TaskOnlyId> response = restTemplatet.exchange(taskProperties.getPort() + "/api/comments/{comment_id}",
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<>() {
                }, commentId);

        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }

        return response.getBody();
    }


}
