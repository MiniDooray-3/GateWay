package com.nhnacademy.edu.minidooray.domain.project;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectModify {
    private final Long projectId;
    private final String status;
}
