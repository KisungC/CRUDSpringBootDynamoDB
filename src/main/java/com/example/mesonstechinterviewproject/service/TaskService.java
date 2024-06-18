package com.example.mesonstechinterviewproject.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.example.mesonstechinterviewproject.persistence.entity.Task;
import com.example.mesonstechinterviewproject.persistence.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;        //Putting taskRepo in as per specification
    private final DynamoDBMapper mapper;    //This is from AWS SDK

    /*
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }*/

    //for AWS SDK
    public TaskService(TaskRepo taskRepo, DynamoDBMapper mapper)
    {
        this.taskRepo = taskRepo;
        this.mapper = mapper;
    }


    public List<Task> getAllTask() {

        return (List<Task>) taskRepo.findAll();

        /*
        List<Task> taskList = new ArrayList<>();

        //for AWS SDK
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<Task> tasks = mapper.scan(Task.class, scanExpression);
        for (Task task : tasks) {
            taskList.add(task);
        }
        return taskList;
        */
    }

    public Task putTaskById(String id,Task updatedTask){

        if(taskRepo.findById(id).isPresent())
        {
            taskRepo.save(updatedTask);
        }
        else{
            throw new RuntimeException("ID not found");
        }

        return updatedTask;
        /*
        for AWS SDK
        Task existingTask = mapper.load(Task.class, id);                //find id
        if(existingTask != null && existingTask.getId() != null){
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setDueDate(updatedTask.getDueDate());
            mapper.save(existingTask);
        }
        return existingTask;
        */
    }

    public void create(Task task) {
        taskRepo.save(task);

        /*
        for AWS SDK
        mapper.save(task);
         */
    }

    public void deleteTask(String id) {

        if(taskRepo.findById(id).isPresent())
        {
            taskRepo.deleteById(id);
        }
        else {
            throw new RuntimeException("ID not found");
        }

        /*
        for AWS SDK
        Task task = mapper.load(Task.class, id);
        if(task != null)
        {
            mapper.delete(task);
        }
        else {
            throw new RuntimeException("ID not found. Please validate ID.");
        }
         */
    }
}
