package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.task.TaskRegister;

public interface TaskAdaptor {

    void createTask(TaskRegister taskRegister);

    void deleteTask(Long taskId);
}
