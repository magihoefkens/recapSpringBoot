package com.example.recapspringboot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ToDoServiceTest {
    ToDoRepository toDoRepository=mock(ToDoRepository.class);
    ToDoService toDoService=new ToDoService(toDoRepository);

    @Test
    void getAllToDos_whenCalled_thenReturnsAllPersons(){
        //GIVEN
        List<ToDo> expected=List.of(new ToDo("1","Tanzen",ToDoStatus.OPEN));
        when(toDoRepository.findAll()).thenReturn(expected);
        //WHEN
        List<ToDo> actual=toDoService.getAllToDos();
        //THEN
        verify(toDoRepository).findAll();
        assertEquals(expected,actual);

    }
}
