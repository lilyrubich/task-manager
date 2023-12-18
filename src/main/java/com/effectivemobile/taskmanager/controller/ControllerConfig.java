package com.effectivemobile.taskmanager.controller;

import com.effectivemobile.taskmanager.repository.CommentRepository;
import com.effectivemobile.taskmanager.repository.TaskRepository;
import com.effectivemobile.taskmanager.repository.UserRepository;
import com.effectivemobile.taskmanager.service.CommentService;
import com.effectivemobile.taskmanager.service.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Bean
    public TaskService taskService(TaskRepository taskRepository, UserRepository userRepository) {
        return new TaskService(taskRepository, userRepository);
    }

    @Bean
    public CommentService commentService(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository) {
        return new CommentService(commentRepository, userRepository, taskRepository);
    }
}
