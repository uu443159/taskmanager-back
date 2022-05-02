package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.model.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskService {

    void addTask(Task task);

    void updateTask(Task task);

    void removeTask(Task task);

    Task showTaskById(long id);

    List<Task> showAllTasks();

    @Transactional
    List<Task> showTasksByUserName(String userName);

    void removeTaskById(long id);

}
