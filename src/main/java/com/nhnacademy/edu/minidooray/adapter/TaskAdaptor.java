package com.nhnacademy.edu.minidooray.adapter;

import com.nhnacademy.edu.minidooray.domain.task.Task;
import com.nhnacademy.edu.minidooray.domain.task.TaskModifyRequest;
import com.nhnacademy.edu.minidooray.domain.task.TaskRegister;
import com.nhnacademy.edu.minidooray.domain.task.TaskViewRequest;
import java.util.List;

public interface TaskAdaptor {

    void createTask(TaskRegister taskRegister);

    void deleteTask(Long taskId);

    Task getTask(Long taskId);

    List<TaskViewRequest> getTasks(Long projectId);

    void modifyTask(Long taskId, TaskModifyRequest taskModifyRequest);
}
