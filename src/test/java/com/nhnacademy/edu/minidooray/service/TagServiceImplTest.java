package com.nhnacademy.edu.minidooray.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.minidooray.adapter.TagAdaptor;
import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import com.nhnacademy.edu.minidooray.domain.tag.ModifyTag;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TagServiceImplTest {

    @Autowired
    TagService tagService;

    @MockBean
    TagAdaptor tagAdaptor;

    @Test
    @DisplayName("태그 등록 - 성공")
    void testRegisterTag() {
        Long projectId = 1L;
        RegisterTag tag = new RegisterTag("tag_test", projectId);

        doNothing().when(tagAdaptor).registerTag(tag);

        tagService.registerTag(tag);

        verify(tagAdaptor, times(1)).registerTag(tag);
    }

    @Test
    @DisplayName("태그 수정 - 성공")
    void testModifyTag() {
        Long tagId = 1L;
        ModifyTag tag = new ModifyTag("tag_12");

        doNothing().when(tagAdaptor).modifyTag(tagId, tag);

        tagService.modifyTag(tagId, tag);

        verify(tagAdaptor, times(1)).modifyTag(tagId, tag);
    }

    @Test
    @DisplayName("태그 삭제 - 성공")
    void testDeleteTag() {
        Long tagId = 1L;

        doNothing().when(tagAdaptor).deleteTag(tagId);

        tagService.deleteTag(tagId);

        verify(tagAdaptor, times(1)).deleteTag(tagId);
    }

    @Test
    @DisplayName("태그 전부 조회 - 성공")
    void testGetTags() {
        Long projectId = 1L;
        List<GetTag> tags = List.of(
                new GetTag("tag1", 1L),
                new GetTag("tag2", 2L));

        when(tagAdaptor.getTags(projectId)).thenReturn(tags);

        List<GetTag> result = tagService.getTags(projectId);

        assertThat(result).isEqualTo(tags);
        verify(tagAdaptor, times(1)).getTags(projectId);
    }

    @Test
    @DisplayName("태그 1개 조회 - 성공")
    void testGetTag() {
        Long tagId = 1L;
        GetTag tag = new GetTag("tag1", tagId);

        when(tagAdaptor.getTag(tagId)).thenReturn(tag);

        GetTag result = tagService.getTag(tagId);

        assertThat(result).isEqualTo(tag);
        verify(tagAdaptor, times(1)).getTag(tagId);
    }
}