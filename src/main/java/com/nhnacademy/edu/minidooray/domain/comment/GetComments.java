package com.nhnacademy.edu.minidooray.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetComments {
    private Long commentId;
    private String content;
    private String memberId;
}
