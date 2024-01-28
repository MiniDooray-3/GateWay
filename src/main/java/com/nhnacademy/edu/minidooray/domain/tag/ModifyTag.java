package com.nhnacademy.edu.minidooray.domain.tag;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ModifyTag {
    @NotBlank
    private String tagName;
}
