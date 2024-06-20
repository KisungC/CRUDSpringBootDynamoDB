package com.example.mesonstechinterviewproject.persistence.repository;

import com.example.mesonstechinterviewproject.persistence.entity.Task;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface TaskRepo extends CrudRepository<Task, String> {
//using the default functions from CrudRepository
}
