package com.stefanini.taskmanager.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddTaskRequest {
    @JsonProperty(value = "username")
    private String userName;
    @JsonProperty(value = "title")
    private String taskTitle;
    @JsonProperty(value = "description")
    private String taskDescription;

    public AddTaskRequest(String userName, String taskTitle, String taskDescription) {
        this.userName = userName;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }
}
