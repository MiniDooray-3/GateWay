package com.nhnacademy.edu.minidooray.domain.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentIdAndContent {
    private Long commentId;
    private String commentContent;
}
