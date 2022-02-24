package com.stefanini.taskmanager.controller;

import com.stefanini.taskmanager.model.Task;
import com.stefanini.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/save")
    public String addTaskToUser(@RequestParam String userName, @RequestParam String title, @RequestParam String description) {
        Task task = new Task();

        task.setUserName(userName);
        task.setTitle(title);
        task.setDescription(description);

        taskService.addTask(task);

        return "Saved";
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
    public List<Task> getAllTasks() {
        return taskService.showAllTasks();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable(value = "id") Long taskId) {
        taskService.removeTaskById(taskId);

        return "Deleted";
    }
}
