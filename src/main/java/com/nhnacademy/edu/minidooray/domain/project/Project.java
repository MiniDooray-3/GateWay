package com.nhnacademy.edu.minidooray.domain.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Project {
    Long projectId;
    String projectName;
    String projectStatus;
}
