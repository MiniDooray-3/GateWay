package com.nhnacademy.edu.minidooray.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentRegister {
    private String content;
    private Long taskId;
    private String memberId;
    private Long projectId;
}
