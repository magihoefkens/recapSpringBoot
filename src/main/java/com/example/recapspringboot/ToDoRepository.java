package com.example.recapspringboot;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo,String>{
   // public Optional<ToDo> findByStatus(String status);
}
