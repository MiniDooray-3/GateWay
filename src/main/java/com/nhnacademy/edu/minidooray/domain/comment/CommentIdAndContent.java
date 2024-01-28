package com.nhnacademy.edu.minidooray.domain.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentIdAndContent {
    private Long commentId;
    private String commentContent;
}
