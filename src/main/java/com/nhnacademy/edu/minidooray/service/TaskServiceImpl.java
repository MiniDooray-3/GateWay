package com.nhnacademy.edu.minidooray.service;

import com.nhnacademy.edu.minidooray.adapter.TaskAdaptor;
import com.nhnacademy.edu.minidooray.domain.task.Task;
import com.nhnacademy.edu.minidooray.domain.task.TaskModifyRequest;
import com.nhnacademy.edu.minidooray.domain.task.TaskRegister;
import com.nhnacademy.edu.minidooray.domain.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.domain.task.TaskViewRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

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

    @Override
    public Task getTask(Long taskId) {
        return taskAdaptor.getTask(taskId);
    }

    @Override
    public List<TaskViewRequest> getTasks(Long projectId) {
        return taskAdaptor.getTasks(projectId);
    }

    @Override
    public void modifyTask(Long taskId, TaskModifyRequest taskModifyRequest) {
        taskAdaptor.modifyTask(taskId, taskModifyRequest);
    }
}
