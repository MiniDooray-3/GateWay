package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.domain.task.TaskRegisterRequest;

public interface TaskService {

    void registerTask(Long projectId, TaskRegisterRequest taskRegisterRequest);

    void removeTask(Long taskId);
}
