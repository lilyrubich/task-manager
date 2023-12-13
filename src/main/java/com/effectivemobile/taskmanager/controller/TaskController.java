package com.effectivemobile.taskmanager.controller;

import com.effectivemobile.taskmanager.enums.TaskStatus;
import com.effectivemobile.taskmanager.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*API должно позволять получать задачи конкретного автора или исполнителя, а
        также все комментарии к ним. Необходимо обеспечить фильтрацию и
        пагинацию вывода.*/
@RestController
@RequestMapping("/task")
public class TaskController {

    //для всех юзеров
    @GetMapping("/getTasksByAssignee")
    public List<Task> getTasksByAssigneeUser(Long id) {
        return null;
    }

    //для всех юзеров
    @GetMapping("/getTasksByReporter")
    public List<Task> getTasksByReporterUser(Long id) {
        return null;
    }

    @PostMapping("/add")
    public Task createTask(@RequestBody Task task) {
        return null;
    }

    //только для создалей (task reporter)
    @PostMapping("/update")
    public Task updateTask(@RequestBody Task task) {
        return null;
    }

    //только для исполнителей (task assignee)
    @PostMapping("/updateStatus")
    public Task updateStatus(@RequestParam Long id, @RequestParam TaskStatus status) {
        return null;
    }

    //только для создалей (task reporter)
    @DeleteMapping("/delete")
    public void deleteTask(@RequestParam Long id) {

    }


}
