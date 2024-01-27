package com.nhnacademy.edu.minidooray.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetComments {
    private Long commentId;
    private String content;
    private String memberId;
}
