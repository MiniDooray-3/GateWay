package com.nhnacademy.edu.minidooray.controller;

import com.nhnacademy.edu.minidooray.domain.comment.CommentModifyRequest;
import com.nhnacademy.edu.minidooray.domain.comment.CommentRegisterRequest;
import com.nhnacademy.edu.minidooray.service.CommentService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public String registerComment(@SessionAttribute("LOGIN_ID") String memberId,
                                @SessionAttribute("projectId") Long projectId,
                                @ModelAttribute CommentRegisterRequest commentRegisterRequest) {
        commentService.registerComment(memberId, commentRegisterRequest, projectId);

        return "redirect:/tasks/" + commentRegisterRequest.getTaskId();
    }

    @GetMapping("/comments/{comment_id}/modify")
    public String modifyCommentForm(@PathVariable("comment_id") Long commentId,
                                    Model model) {
        model.addAttribute("comment", commentService.getComment(commentId));

        return "modifyCommentForm";
    }

    @PostMapping("/comments/{comment_id}/modify")
    public String modifyComment(@PathVariable("comment_id") Long commentId,
                                @ModelAttribute CommentModifyRequest commentModifyRequest) {
        Long taskId = commentService.modifyComment(commentId, commentModifyRequest);

        return "redirect:/tasks/" + taskId;
    }

    @GetMapping("/comments/{comment_id}/delete")
    public String deleteComment(@PathVariable("comment_id") Long commentId) {
        Long taskId = commentService.deleteComment(commentId);

        return "redirect:/tasks/" + taskId;
    }
}
