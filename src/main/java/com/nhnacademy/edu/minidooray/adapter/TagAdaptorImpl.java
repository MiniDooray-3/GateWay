package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.tag.TagRequest;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class TagAdaptorImpl implements TagAdaptor {

    private final RestTemplate restTemplate;

    private final TaskProperties taskProperties;

    public TagAdaptorImpl(RestTemplate restTemplate, TaskProperties taskProperties) {
        this.restTemplate = restTemplate;
        this.taskProperties = taskProperties;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTag(RegisterTag tag) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<RegisterTag> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/tags",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (!HttpStatus.CREATED.equals(exchange.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void modifyTag(Long tagId, TagRequest tag) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<TagRequest> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, tagId);

        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteTag(Long tagId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Void> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, tagId);

        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<TagRequest> getTags(Long projectId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<TagRequest>> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{project_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, projectId);

        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return exchange.getBody();
    }

    @Override
    public TagRequest getTag(Long tagId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<TagRequest> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, tagId);

        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return exchange.getBody();
    }
}
