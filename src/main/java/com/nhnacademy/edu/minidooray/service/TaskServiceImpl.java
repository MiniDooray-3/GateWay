package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.TaskAdaptor;
import com.nhnacademy.edu.minidooray.domain.task.TaskRegister;
import com.nhnacademy.edu.minidooray.domain.task.TaskRegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskAdaptor taskAdaptor;

    public TaskServiceImpl(TaskAdaptor taskAdaptor) {
        this.taskAdaptor = taskAdaptor;
    }

    @Override
    public void registerTask(Long projectId, TaskRegisterRequest taskRegisterRequest) {
        TaskRegister taskRegister = new TaskRegister(taskRegisterRequest.getTaskTitle(),
                taskRegisterRequest.getTaskContent(),
                projectId,
                taskRegisterRequest.getMileStoneId(),
                taskRegisterRequest.getTagId());

        taskAdaptor.createTask(taskRegister);
    }

    @Override
    public void removeTask(Long taskId) {
        taskAdaptor.deleteTask(taskId);
    }
}
