package com.example.recapspringboot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class ToDoController {
    private final ToDoService toDoService;
    ToDoController(ToDoService toDoService){
        this.toDoService=toDoService;
    }
    @GetMapping
    List<ToDo> getAll(){
        return toDoService.getAllToDos();

    }
    @GetMapping("{id}")
    ToDo getToDoById(@PathVariable String id)throws ToDoNotFoundException{
        return toDoService.getToDoById(id);
    }
    @PostMapping
    ToDo postToDo(@RequestBody ToDo toDo){
        return toDoService.saveToDo(toDo);
    }
    @PutMapping("{id}")
    ToDo update(@PathVariable String id,@RequestBody ToDo toDo) throws ToDoNotFoundException{
        if(!toDo.id().equals(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id in the url does not match the request body's id");
        }
        return toDoService.update(toDo);
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable String id) throws ToDoNotFoundException{

        toDoService.delete(id);
    }



}
