package com.nhnacademy.edu.minidooray.domain.task;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class TaskRegister {
    private String taskTitle;
    private String taskContent;
    private Long projectId;
    private Long milestoneId;
    private List<Long> tagId;
}
