package com.nhnacademy.edu.minidooray.domain.task;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRegisterRequest {
    private String taskTitle;
    private String taskContent;
    private Long mileStoneId;
    private List<Long> tagId;
}
