package com.stefanini.taskmanager.response;

import lombok.Data;

@Data
public class TaskResponse {
    private long taskId;
    private String taskTitle;
    private String taskDescription;

    public TaskResponse(long taskId, String taskTitle, String taskDescription) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }
}
