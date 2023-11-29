package com.example.recapspringboot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.CharacterCodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository repository;

    public List<ToDo> getAllToDos(){
        return repository.findAll();
    }
    public ToDo saveToDo(ToDo toDo){
        return repository.save(toDo);
    }

    public ToDo getToDoById(String id) throws ToDoNotFoundException{
        return repository.findById(id)
                .orElseThrow(()->new ToDoNotFoundException("ToDo mit der id: "+ id+" wurde nicht gefunden"));
    }
    public ToDo update(ToDo toDo)throws ToDoNotFoundException {
        ToDo findToDo=getToDoById(toDo.id());
        return repository.save(toDo);

    }
    public void delete(String id) throws ToDoNotFoundException{
        ToDo deleteToDo=getToDoById(id);
        repository.delete(deleteToDo);
    }


}
