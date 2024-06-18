package com.example.mesonstechinterviewproject.api.controller;

import com.example.mesonstechinterviewproject.persistence.entity.Task;
import com.example.mesonstechinterviewproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }

    @PostMapping("/")
    public String createTask(@RequestBody Task task)
    {
        taskService.create(task);
        return "success";
    }

    @PutMapping("/{id}")
    public Task putTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return taskService.putTaskById(id,updatedTask);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "success";
    }

}
