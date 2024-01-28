package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.domain.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.domain.comment.CommentRegister;
import com.nhnacademy.edu.minidooray.domain.comment.GetComments;
import com.nhnacademy.edu.minidooray.domain.task.TaskOnlyId;
import java.util.List;

public interface CommentAdaptor {
    void createComment(CommentRegister commentRegister);

    List<GetComments> getComments(Long taskId);

    CommentIdAndContent getComment(Long commentId);

    TaskOnlyId modifyComment(Long commentId, CommentModifyRequest commentModifyRequest);

    TaskOnlyId deleteComment(Long commentId);
}
