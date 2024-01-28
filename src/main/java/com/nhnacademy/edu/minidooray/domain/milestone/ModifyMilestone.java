package com.nhnacademy.edu.minidooray.domain.milestone;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyMilestone {
    @NotBlank
    private String mileStoneStatus;
}
