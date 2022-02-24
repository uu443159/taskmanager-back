package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.model.Task;

import java.util.List;

public interface TaskService {

    void addTask(Task task);

    void updateTask(Task task);

    void removeTask(Task task);

    Task showTaskById(long id);

    List<Task> showAllTasks();

    void removeTaskById(long id);

}
