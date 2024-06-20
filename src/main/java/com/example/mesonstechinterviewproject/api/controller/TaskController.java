package com.example.mesonstechinterviewproject.api.controller;

import com.example.mesonstechinterviewproject.persistence.entity.Task;
import com.example.mesonstechinterviewproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {       //gets everything from the database
        return taskService.getAllTask();    //goes into taskService Module for business logic and returns data to the client
    }

    @PostMapping("/")
    public ResponseEntity<String> createTask(@RequestBody Task task)    //creates a task
    {
        taskService.create(task);
        return ResponseEntity.ok().body("{\"message\": \"success\"}");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putTask(@PathVariable String id, @RequestBody Task updatedTask) {     //update a task
        taskService.putTaskById(id,updatedTask);
        return ResponseEntity.ok().body("{\"message\": \"success\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {     //delete a task
        taskService.deleteTask(id);
        return ResponseEntity.ok().body("{\"message\": \"success\"}");
    }

}
