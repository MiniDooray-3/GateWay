package com.nhnacademy.edu.minidooray.domain.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Project {
    Long projectId;
    String projectName;
    String projectStatus;
}
