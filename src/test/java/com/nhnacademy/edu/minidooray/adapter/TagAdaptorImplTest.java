package com.nhnacademy.edu.minidooray.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import com.nhnacademy.edu.minidooray.domain.tag.ModifyTag;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import com.nhnacademy.edu.minidooray.property.TaskProperties;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class TagAdaptorImplTest {

    @Autowired
    TagAdaptor tagAdaptor;

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    TaskProperties taskProperties;

    @Test
    void testRegisterTag() {
        RegisterTag tag = new RegisterTag("tag1", 1L);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterTag> requestEntity = new HttpEntity<>(tag, httpHeaders);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);

        when(restTemplate.exchange(
                taskProperties.getPort() + "/api/tags",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Void>() {}
        )).thenReturn(responseEntity);

        tagAdaptor.registerTag(tag);

        verify(restTemplate).exchange(
                taskProperties.getPort() + "/api/tags",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Void>() {}
        );
    }

    @Test
    void testModifyTag() {
        Long tagId = 1L;
        ModifyTag tag = new ModifyTag("tag1");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ModifyTag> requestEntity = new HttpEntity<>(tag, httpHeaders);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);

        when(restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<Void>() {},
                tagId
        )).thenReturn(responseEntity);

        tagAdaptor.modifyTag(tagId, tag);

        verify(restTemplate).exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<Void>() {},
                tagId
        );
    }

    @Test
    void testDeleteTag() {
        Long tagId = 1L;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ModifyTag> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);

        when(restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Void>() {},
                tagId
        )).thenReturn(responseEntity);

        tagAdaptor.deleteTag(tagId);

        verify(restTemplate).exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Void>() {},
                tagId
        );
    }

    @Test
    void testGetTags() {
        Long projectId = 1L;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        List<GetTag> expectedTags = List.of(
                new GetTag("tag1", 1L),
                new GetTag("tag2", 2L),
                new GetTag("tag3", 3L)
        );

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<GetTag>> responseEntity = new ResponseEntity<>(expectedTags, HttpStatus.CREATED);

        when(restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{project_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<GetTag>>() {},
                projectId
        )).thenReturn(responseEntity);

        List<GetTag> actualTags = tagAdaptor.getTags(projectId);

        verify(restTemplate).exchange(
                taskProperties.getPort() + "/api/tags/{project_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<GetTag>>() {},
                projectId
        );
        assertThat(responseEntity.getBody()).isEqualTo(actualTags);
    }

    @Test
    void testGetTag() {
        Long tagId = 1L;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        GetTag expectedTag = new GetTag("tag1", tagId);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<GetTag> responseEntity = new ResponseEntity<>(expectedTag, HttpStatus.CREATED);

        when(restTemplate.exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<GetTag>() {},
                tagId
        )).thenReturn(responseEntity);

        GetTag actualTag = tagAdaptor.getTag(tagId);

        verify(restTemplate).exchange(
                taskProperties.getPort() + "/api/tags/{tag_id}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<GetTag>() {},
                tagId
        );
        assertThat(responseEntity.getBody()).isEqualTo(actualTag);
    }
}