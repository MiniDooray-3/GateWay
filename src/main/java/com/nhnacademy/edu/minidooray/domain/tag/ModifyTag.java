package com.nhnacademy.edu.minidooray.domain.tag;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ModifyTag {
    @NotBlank
    private String tagName;
}