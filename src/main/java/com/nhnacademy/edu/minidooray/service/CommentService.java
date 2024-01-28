package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.domain.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.domain.comment.CommentRegisterRequest;
import com.nhnacademy.edu.minidooray.domain.comment.GetComments;
import java.util.List;

public interface CommentService {
    void registerComment(String memberId, CommentRegisterRequest commentRegisterRequest, Long projectId);

    List<GetComments> getComments(Long taskId);

    CommentIdAndContent getComment(Long commentId);

    Long modifyComment(Long commentId, CommentModifyRequest commentModifyRequest);

    Long deleteComment(Long commentId);
}
