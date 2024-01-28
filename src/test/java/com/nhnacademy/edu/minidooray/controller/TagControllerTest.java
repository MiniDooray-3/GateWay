package com.nhnacademy.edu.minidooray.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.edu.minidooray.domain.member.GetMember;
import com.nhnacademy.edu.minidooray.domain.tag.GetTag;
import com.nhnacademy.edu.minidooray.domain.tag.ModifyTag;
import com.nhnacademy.edu.minidooray.domain.tag.RegisterTag;
import com.nhnacademy.edu.minidooray.service.MemberService;
import com.nhnacademy.edu.minidooray.service.TagService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TagControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TagService tagService;

    @MockBean
    MemberService memberService;

    @BeforeEach
    void setup() {
        GetMember member = new GetMember("ADMIN", "tester");
        given(memberService.getMember(anyString(), any())).willReturn(member);
    }

    @Test
    @DisplayName("태그 전부 조회 - 성공")
    void testGetTags() throws Exception {
        Long projectId = 1L;
        List<GetTag> expectedTags = List.of(
                new GetTag("tag1", 1L),
                new GetTag("tag2", 2L));

        given(tagService.getTags(projectId)).willReturn(expectedTags);

        mockMvc.perform(get("/tags/list")
                        .sessionAttr("projectId", projectId)
                        .sessionAttr("LOGIN_ID", "tester"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tags", expectedTags))
                .andExpect(view().name("tagList"));
    }

    @Test
    @DisplayName("태그 등록 - 성공")
    void testRegisterTag() throws Exception {
        Long projectId = 1L;
        RegisterTag tag = new RegisterTag("tag_test", projectId);

        mockMvc.perform(post("/tags/register")
                        .param("tagName", tag.getTagName())
                        .param("projectId", tag.getProjectId().toString())
                        .sessionAttr("LOGIN_ID", "tester")
                        .sessionAttr("projectId", projectId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tags/list"));

        verify(tagService).registerTag(any());
    }

    @Test
    @DisplayName("태그 수정 페이지 - 성공")
    void testTagModifyForm() throws Exception {
        Long tagId = 1L;
        Long projectId = 1L;
        String tagName = "tag";

        mockMvc.perform(get("/tags/{tag_id}/modify", tagId)
                        .param("tagName", tagName)
                        .sessionAttr("LOGIN_ID", "tester")
                        .sessionAttr("projectId", projectId))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tagId", tagId))
                .andExpect(model().attribute("tagName", tagName))
                .andExpect(view().name("tagModifyForm"));
    }

    @Test
    @DisplayName("태그 수정 - 성공")
    void testModifyTag() throws Exception {
        Long tagId = 1L;
        Long projectId = 1L;
        ModifyTag tag = new ModifyTag("modifiedTag");

        mockMvc.perform(post("/tags/{tag_id}/modify", tagId)
                        .param("tagName", tag.getTagName())
                        .sessionAttr("LOGIN_ID", "tester")
                        .sessionAttr("projectId", projectId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tags/list"));

        verify(tagService).modifyTag(any(Long.class), any(ModifyTag.class));
    }

    @Test
    @DisplayName("태그 삭제 - 성공")
    void testDeleteTag() throws Exception {
        Long tagId = 1L;
        Long projectId = 1L;

        mockMvc.perform(get("/tags/{tag_id}/delete", tagId)
                        .sessionAttr("LOGIN_ID", "tester")
                        .sessionAttr("projectId", projectId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tags/list"));

        verify(tagService).deleteTag(tagId);
    }
}