package com.nhnacademy.edu.minidooray.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.edu.minidooray.adapter.TaskAdaptor;
import com.nhnacademy.edu.minidooray.domain.task.Task;
import com.nhnacademy.edu.minidooray.domain.task.TaskModifyRequest;
import com.nhnacademy.edu.minidooray.domain.task.TaskRegisterRequest;
import com.nhnacademy.edu.minidooray.domain.task.TaskViewRequest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TaskServiceImplTest {

    @Autowired
    TaskService taskService;

    @MockBean
    TaskAdaptor taskAdaptor;

    @Test
    void testRegisterTask() {
        doNothing().when(taskAdaptor).createTask(any());

        taskService.registerTask(1L,
                new TaskRegisterRequest(
                        "title",
                        "content",
                        1L, null
                ));

        verify(taskAdaptor, times(1)).createTask(any());
    }

    @Test
    void testRemoveTask() {
        doNothing().when(taskAdaptor).deleteTask(any());

        taskService.removeTask(1L);

        verify(taskAdaptor, times(1)).deleteTask(any());
    }

    @Test
    void testGetTask() {
        Task task = new Task(1L, "title", "content",
                new Task.Project(1L, "project"),
                new Task.MileStone("활성", 1L));

        given(taskAdaptor.getTask(any())).willReturn(task);

        Task result = taskService.getTask(1L);

        assertEquals(task, result);

        verify(taskAdaptor, times(1)).getTask(any());
    }

    @Test
    void testGetTasks() {
        List<TaskViewRequest> tasks = Arrays.asList(new TaskViewRequest(1L, "title",
                new TaskViewRequest.MileStone("개발", 1L)));

        given(taskAdaptor.getTasks(any())).willReturn(tasks);

        List<TaskViewRequest> result = taskService.getTasks(1L);

        assertEquals(tasks, result);

        verify(taskAdaptor, times(1)).getTasks(any());
    }

    @Test
    void testModifyTask() {
        doNothing().when(taskAdaptor).modifyTask(any(), any());

        taskService.modifyTask(1L, new TaskModifyRequest("content", 1L, Arrays.asList(1L)));

        verify(taskAdaptor, times(1)).modifyTask(any(), any());
    }

}