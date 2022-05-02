package com.stefanini.taskmanager;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);

        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:3306/taskmanager", "root", null).load();
        flyway.migrate();
    }

}
