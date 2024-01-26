package com.nhnacademy.edu.minidooray.domain.task;

public class Task {
    String taskTitle;
    String taskContent;

    public static class ProjectId {
        Long projectId;
        String projectName;
    }

    public static class MileStoneId {
        Long mileStoneId;
        String mileStoneStatus;
    }
}
