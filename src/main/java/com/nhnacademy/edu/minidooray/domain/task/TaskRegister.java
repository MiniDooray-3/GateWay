package com.nhnacademy.edu.minidooray.domain.task;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRegister {
    private String taskTitle;
    private String taskContent;
    private Long projectId;
    private Long milestoneId;
    private List<Long> tagId;
}
