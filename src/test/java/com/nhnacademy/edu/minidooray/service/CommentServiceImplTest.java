package com.nhnacademy.edu.minidooray.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.minidooray.adapter.CommentAdaptor;
import com.nhnacademy.edu.minidooray.domain.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.domain.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.domain.comment.CommentRegisterRequest;
import com.nhnacademy.edu.minidooray.domain.comment.GetComments;
import com.nhnacademy.edu.minidooray.domain.task.TaskOnlyId;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    CommentService commentService;

    @MockBean
    CommentAdaptor commentAdaptor;

    @Test
    void testRegisterComment() {
        doNothing().when(commentAdaptor).createComment(any());

        commentService.registerComment("tester",
                new CommentRegisterRequest(1L, "content"),
                1L);

        verify(commentAdaptor, times(1)).createComment(any());
    }

    @Test
    void testGetComments() {
        List<GetComments> comments = Arrays.asList(new GetComments(1L, "content", "tester"));

        given(commentAdaptor.getComments(any())).willReturn(comments);

        List<GetComments> result = commentService.getComments(1L);

        assertEquals(comments, result);

        verify(commentAdaptor, times(1)).getComments(any());
    }

    @Test
    void testGetComment() {
        CommentIdAndContent comment = new CommentIdAndContent(1L, "content");

        given(commentAdaptor.getComment(any())).willReturn(comment);

        CommentIdAndContent result = commentService.getComment(1L);

        assertEquals(comment, result);

        verify(commentAdaptor, times(1)).getComment(any());
    }

    @Test
    void testModifyComment() {
        given(commentAdaptor.modifyComment(any(), any())).willReturn(new TaskOnlyId(1L));

        Long result = commentService.modifyComment(1L, new CommentModifyRequest("content"));

        assertEquals(1L, result);

        verify(commentAdaptor, times(1)).modifyComment(any(), any());
    }

    @Test
    void testDeleteComment() {
        given(commentAdaptor.deleteComment(any())).willReturn(new TaskOnlyId(1L));

        Long result = commentService.deleteComment(1L);

        assertEquals(1L, result);

        verify(commentAdaptor, times(1)).deleteComment(any());
    }
}