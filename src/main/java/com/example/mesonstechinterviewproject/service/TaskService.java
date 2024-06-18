package com.example.mesonstechinterviewproject.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.mesonstechinterviewproject.persistence.entity.Task;
import com.example.mesonstechinterviewproject.persistence.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final DynamoDBMapper mapper;

    public TaskService(TaskRepo taskRepo, DynamoDBMapper mapper)
    {
        this.taskRepo = taskRepo;
        this.mapper = mapper;
    }

    public Optional<Task> getTaskById(String id){
        return taskRepo.findById(id);
    }

    public Task getAllTask() {
        Task task = new Task("test1", "desc1", "test1", "test2");

        mapper.save(task);

        return task;
    }

    public void save(Task task) {
        taskRepo.save(task);
    }

    public void deleteTask(String id) {
        taskRepo.deleteById(id);
    }
}
