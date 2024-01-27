package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.task.Task;
import com.nhnacademy.edu.minidooray.domain.task.TaskModifyRequest;
import com.nhnacademy.edu.minidooray.domain.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.domain.task.TaskViewRequest;
import java.util.List;

public interface TaskService {

    void registerTask(Long projectId, TaskRegisterRequest taskRegisterRequest);

    void removeTask(Long taskId);

    Task getTask(Long taskId);

    List<TaskViewRequest> getTasks(Long projectId);

    void modifyTask(Long taskId, TaskModifyRequest taskModifyRequest);
}
