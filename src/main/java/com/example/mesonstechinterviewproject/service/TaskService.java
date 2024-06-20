package com.example.mesonstechinterviewproject.service;

//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.mesonstechinterviewproject.exception.DatabaseConnectionException;
import com.example.mesonstechinterviewproject.persistence.entity.Task;
import com.example.mesonstechinterviewproject.persistence.repository.TaskRepo;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;        //Putting taskRepo in as per specification
    //private final DynamoDBMapper mapper;    This is from AWS SDK

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    /*for AWS SDK
    public TaskService(TaskRepo taskRepo, DynamoDBMapper mapper)
    {
        this.taskRepo = taskRepo;
        this.mapper = mapper;
    }*/


    public List<Task> getAllTask() {

            return (List<Task>) taskRepo.findAll(); //returns array of lists to the controller

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

        try{
            Task taskDB = taskRepo.findById(id).get();      //get the task id from the client

            if(taskRepo.findById(id).isPresent())
            {
                if(!updatedTask.getTitle().isBlank())           //if the client's task field has some information, then update
                    taskDB.setTitle(updatedTask.getTitle());

                if(!updatedTask.getDescription().isBlank())
                    taskDB.setDescription(updatedTask.getDescription());

                if(!updatedTask.getDueDate().isBlank())
                    taskDB.setDueDate(updatedTask.getDueDate());

                if(!updatedTask.getStatus().isBlank())
                    taskDB.setStatus(updatedTask.getStatus());

                taskRepo.save(taskDB);
                return updatedTask;
            }
            else{
                throw new RuntimeException("ID not found");
            }
        }
        catch (DataAccessException e) {
            throw new DatabaseConnectionException("Unable to connect to the database.");
        }

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

    public void create(Task task) {         //create a task from the client info
        try{
            taskRepo.save(task);
        }
        catch (DataAccessException e) {
            throw new DatabaseConnectionException("Unable to connect to the database.");
        }
        /*
        for AWS SDK
        mapper.save(task);
         */
    }

    public void deleteTask(String id) {     //deletes a task by id

        try {
            if(taskRepo.findById(id).isPresent())
            {
                taskRepo.deleteById(id);
            }
            else {
                throw new RuntimeException("ID not found");
            }
        }

                catch (DataAccessException e){
            throw new DatabaseConnectionException("Database connection error");
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
