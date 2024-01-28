package com.nhnacademy.edu.minidooray.domain.task;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskRegisterRequest {
    private String taskTitle;
    private String taskContent;
    private Long mileStoneId;
    private List<Long> tagId;
}
