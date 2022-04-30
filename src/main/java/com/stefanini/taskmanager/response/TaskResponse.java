package com.stefanini.taskmanager.response;

import lombok.Data;

@Data
public class TaskResponse {
    private String taskTitle;
    private String taskDescription;

    public TaskResponse(String taskTitle, String taskDescription) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }
}
