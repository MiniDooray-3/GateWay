package com.nhnacademy.edu.minidooray.domain.milestone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterMilestone {
    @NotBlank
    private String milestoneStatus;
    @NotNull
    private Long projectId;
}
