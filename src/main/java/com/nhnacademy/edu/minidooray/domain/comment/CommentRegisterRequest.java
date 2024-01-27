package com.nhnacademy.edu.minidooray.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRegisterRequest {
    private Long taskId;
    private String content;
}
