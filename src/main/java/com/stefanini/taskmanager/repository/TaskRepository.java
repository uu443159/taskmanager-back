package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.model.Task;

import java.util.List;

public interface TaskRepository extends GenericRepository<Task> {

    List<Task> getTasksByUserName(String userName);
}
