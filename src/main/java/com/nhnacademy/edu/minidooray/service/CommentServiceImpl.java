package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.CommentAdaptor;
import com.nhnacademy.edu.minidooray.domain.comment.CommentIdAndContent;
import com.nhnacademy.edu.minidooray.domain.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.domain.comment.CommentRegister;
import com.nhnacademy.edu.minidooray.domain.comment.CommentRegisterRequest;
import com.nhnacademy.edu.minidooray.domain.comment.GetComments;
import com.nhnacademy.edu.minidooray.domain.task.TaskOnlyId;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentAdaptor commentAdaptor;

    public CommentServiceImpl(CommentAdaptor commentAdaptor) {
        this.commentAdaptor = commentAdaptor;
    }

    @Override
    public void registerComment(String memberId, CommentRegisterRequest commentRegisterRequest, Long projectId) {
        CommentRegister commentRegister = new CommentRegister(commentRegisterRequest.getContent(),
                commentRegisterRequest.getTaskId(),
                memberId,
                projectId);

        commentAdaptor.createComment(commentRegister);
    }

    @Override
    public List<GetComments> getComments(Long taskId) {
        return commentAdaptor.getComments(taskId);
    }

    @Override
    public CommentIdAndContent getComment(Long commentId) {
        return commentAdaptor.getComment(commentId);
    }

    @Override
    public Long modifyComment(Long commentId, CommentModifyRequest commentModifyRequest) {
        return commentAdaptor.modifyComment(commentId, commentModifyRequest).getTaskId();
    }

    @Override
    public Long deleteComment(Long commentId) {
        return commentAdaptor.deleteComment(commentId).getTaskId();
    }
}
