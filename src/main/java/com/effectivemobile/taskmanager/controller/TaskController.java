package com.effectivemobile.taskmanager.controller;

import com.effectivemobile.taskmanager.enums.TaskStatus;
import com.effectivemobile.taskmanager.service.TaskService;
import com.effectivemobile.taskmanager.transportObject.TaskJsonBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*API должно позволять получать задачи конкретного автора или исполнителя, а
        также все комментарии к ним. Необходимо обеспечить фильтрацию и
        пагинацию вывода.*/
@RestController
@RequestMapping("/taskmanager/task")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //для всех юзеров
    @GetMapping("/getTasksByAssignee")
    public List<TaskJsonBody> getTasksByAssigneeUser(@RequestParam Long id) {
        return taskService.getTasksByAssigneeUser(id);
    }

    //для всех юзеров
    @GetMapping("/getTasksByReporter")
    public List<TaskJsonBody> getTasksByReporterUser(@RequestParam Long id) {
        return taskService.getTaskByReporter(id);
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

    //только для создалей (task reporter)
    @PostMapping("/update")
    public TaskJsonBody updateTask(@Valid @RequestBody TaskJsonBody taskJsonBody) {
        try {
            return taskService.updateTaskByReporter(taskJsonBody);
        } catch (EntityNotFoundException | IllegalAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //только для исполнителей (task assignee)
    @GetMapping("/updateStatus")
    public TaskJsonBody updateStatus(@RequestParam Long id, @RequestParam TaskStatus status) {
        try {
            return taskService.updateStatus(id, status);
        } catch (EntityNotFoundException | IllegalAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //только для создалей (task reporter)
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
