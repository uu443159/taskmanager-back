package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.model.Task;
import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public void addTask(Task task) {
        taskRepository.create(task);
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    @Override
    @Transactional
    public void removeTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    @Transactional
    public Task showTaskById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Task> showAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public void removeTaskById(long id) {
        taskRepository.deleteById(id);
    }
}
