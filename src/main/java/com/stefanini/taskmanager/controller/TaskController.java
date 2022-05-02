package com.stefanini.taskmanager.controller;

import com.stefanini.taskmanager.model.Task;
import com.stefanini.taskmanager.request.AddTaskRequest;
import com.stefanini.taskmanager.response.TaskResponse;
import com.stefanini.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> addTaskToUser(@RequestBody AddTaskRequest request) {
        Task task = new Task();

        task.setUserName(request.getUserName());
        task.setTitle(request.getTaskTitle());
        task.setDescription(request.getTaskDescription());

        taskService.addTask(task);

        String responseMessage = "Task has been created";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/{userName}/all")
    public ResponseEntity<?> getTasksByUserName(@PathVariable String userName) {
        List<Task> tasks = taskService.showTasksByUserName(userName);
        List<TaskResponse> taskResponses = tasks.stream().map(task -> new TaskResponse(task.getId(), task.getTitle(), task.getDescription())).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(taskResponses);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks() {
        List<Task> tasks = taskService.showAllTasks();
        List<TaskResponse> taskResponses = tasks.stream().map(task -> new TaskResponse(task.getId(), task.getTitle(), task.getDescription())).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(taskResponses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable(value = "id") Long taskId) {
        taskService.removeTaskById(taskId);

        String responseMessage = "Deleted";

        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
