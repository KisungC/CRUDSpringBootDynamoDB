package com.example.mesonstechinterviewproject.api.controller;

import com.example.mesonstechinterviewproject.persistence.entity.Task;
import com.example.mesonstechinterviewproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public Task getAllTasks() {
        System.out.println("in get map");
        return taskService.getAllTask();
    }

    @PostMapping("/")
    public String createTask(@RequestBody Task task)
    {
        taskService.save(task);
        return "success";
    }

    @PostMapping("/{id}")
    public Optional<Task> putTask(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "success";
    }

}
