package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import com.nhnacademy.edu.minidooray.domain.tag.ModifyTag;
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
import org.springframework.web.client.RestTemplate;

@Component
public class TagAdaptorImpl implements TagAdaptor {

    private final RestTemplate restTemplate;

    private final TaskProperties taskProperties;

    private static final String TAG_RUD_URL = "/api/tags/{tag_id}";

    public TagAdaptorImpl(RestTemplate restTemplate, TaskProperties taskProperties) {
        this.restTemplate = restTemplate;
        this.taskProperties = taskProperties;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTag(RegisterTag tag) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterTag> requestEntity = new HttpEntity<>(tag, httpHeaders);
        restTemplate.exchange(
                taskProperties.getPort() + "/api/tags",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Void>() {
                });

    }

    @Override
    public void modifyTag(Long tagId, ModifyTag tag) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ModifyTag> requestEntity = new HttpEntity<>(tag, httpHeaders);
        restTemplate.exchange(
                taskProperties.getPort() + TAG_RUD_URL,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<Void>() {
                }, tagId);

    }

    @Override
    public void deleteTag(Long tagId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(
                taskProperties.getPort() + TAG_RUD_URL,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Void>() {
                }, tagId);

    }

    @Override
    public List<GetTag> getTags(Long projectId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetTag>> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{project_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<GetTag>>() {
                }, projectId);

        return exchange.getBody();
    }

    @Override
    public GetTag getTag(Long tagId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<GetTag> exchange = restTemplate.exchange(
                taskProperties.getPort() + TAG_RUD_URL,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, tagId);

        return exchange.getBody();
    }

    @Override
    public List<GetTag> getTagByTaskId(Long taskId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetTag>> exchange = restTemplate.exchange(
                taskProperties.getPort() + "/api/task/tag/{task_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }, taskId);

        return exchange.getBody();
    }

}
