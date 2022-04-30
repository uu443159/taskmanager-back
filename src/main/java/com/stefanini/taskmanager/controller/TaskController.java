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

        return ResponseEntity.status(HttpStatus.CREATED).body("Task has been created");
    }

    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable(value = "id") Long taskId, @RequestParam String userName, @RequestParam String title, @RequestParam String description) {
        Task task = taskService.showTaskById(taskId);

        task.setUserName(userName);
        task.setTitle(title);
        task.setDescription(description);

        taskService.updateTask(task);

        return "Updated";

    }

    @DeleteMapping("/delete")
    public String deleteTask(@RequestBody Task task) {
        taskService.removeTask(task);

        return "Deleted";
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable(value = "id") Long taskId) {
        return taskService.showTaskById(taskId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks() {
        List<Task> tasks = taskService.showAllTasks();
        List<TaskResponse> taskResponses = tasks.stream().map(task -> new TaskResponse(task.getTitle(), task.getDescription())).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.FOUND).body(taskResponses);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable(value = "id") Long taskId) {
        taskService.removeTaskById(taskId);

        return "Deleted";
    }
}
