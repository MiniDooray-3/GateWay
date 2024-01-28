package com.nhnacademy.edu.minidooray.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Task {
    private Long taskId;
    private String taskTitle;
    private String taskContent;
    private Project project;
    private MileStone mileStone;

    @AllArgsConstructor
    @Getter
    public static class Project {
        private Long projectId;
        private String projectName;
    }

    @AllArgsConstructor
    @Getter
    public static class MileStone {
        private String mileStoneStatus;
        private Long mileStoneId;
    }
}
