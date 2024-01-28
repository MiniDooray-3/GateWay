package com.nhnacademy.edu.minidooray.domain.tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterTag {
    @NotBlank
    private String tagName;
    @NotNull
    private Long projectId;
}
