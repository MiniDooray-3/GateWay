package com.nhnacademy.edu.minidooray.domain.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetTag {
    private String tagName;
    private Long tagId;
}