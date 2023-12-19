package com.effectivemobile.taskmanager.controller;

import com.effectivemobile.taskmanager.enums.TaskStatus;
import com.effectivemobile.taskmanager.service.TaskService;
import com.effectivemobile.taskmanager.transportObject.TaskJsonBody;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/taskmanager/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/getTasksByAssignee")
    public List<TaskJsonBody> getTasksByAssigneeUser(@RequestParam Long id,
                                                     @RequestParam("offset") Integer offset,
                                                     @RequestParam("limit") Integer limit) {
        return taskService.getTasksByAssigneeUser(id, PageRequest.of(offset, limit));
    }

    @GetMapping("/getTasksByReporter")
    public List<TaskJsonBody> getTasksByReporterUser(@RequestParam Long id,
                                                     @RequestParam("offset") Integer offset,
                                                     @RequestParam("limit") Integer limit) {
        return taskService.getTaskByReporter(id, PageRequest.of(offset, limit));
    }

    @PostMapping("/add")
    public TaskJsonBody createTask(@Valid @RequestBody TaskJsonBody task) {
        try {
            return taskService.createTask(task);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/update")
    public TaskJsonBody updateTask(@Valid @RequestBody TaskJsonBody taskJsonBody) {
        try {
            return taskService.updateTaskByReporter(taskJsonBody);
        } catch (EntityNotFoundException | IllegalAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/updateStatus")
    public TaskJsonBody updateStatus(@RequestParam Long id, @RequestParam TaskStatus status) {
        try {
            return taskService.updateStatus(id, status);
        } catch (EntityNotFoundException | IllegalAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public void deleteTask(@RequestParam Long id) {
        try {
            taskService.deleteTask(id);
        } catch (EntityNotFoundException | IllegalAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
