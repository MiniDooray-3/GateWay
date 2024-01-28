package com.nhnacademy.edu.minidooray.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class TaskViewRequest {
    private Long taskId;
    private String taskTitle;
    private MileStone mileStone;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MileStone {
        private String mileStoneStatus;
        private Long mileStoneId;
    }
}
