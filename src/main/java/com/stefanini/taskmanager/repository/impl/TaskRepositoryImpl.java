package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.model.Task;
import com.stefanini.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl extends GenericRepositoryImpl<Task> implements TaskRepository {

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    @Override
    protected String getQuery() {
        return "SELECT id, user_name, title, description FROM ";
    }
}
