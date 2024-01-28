package com.nhnacademy.edu.minidooray.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Task {
    private Long taskId;
    private String taskTitle;
    private String taskContent;
    private Project project;
    private MileStone mileStone;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Project {
        private Long projectId;
        private String projectName;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MileStone {
        private String mileStoneStatus;
        private Long mileStoneId;
    }
}
