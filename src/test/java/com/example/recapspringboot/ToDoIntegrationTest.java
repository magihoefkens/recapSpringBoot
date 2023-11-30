package com.example.recapspringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ToDoIntegrationTest {
    final String BASE_URL="http://localhost:8080/api/todo";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;//Macht aus Objekten JSONS und umgekehrt
    @Test
    void getAllToDos_shouldReturnEmptyList_WhenCalledInitially() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }
    @Test
    void getToDoById_shouldReturnToDo_whenCalledWithValidId() throws Exception{
        NewToDoDTO newToDoDTO= new NewToDoDTO("todo");
        String newToDoAsJSON=objectMapper.writeValueAsString(newToDoDTO);
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newToDoAsJSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ToDo savedToDo=objectMapper.readValue(result.getResponse().getContentAsString(),ToDo.class);
        String toDoAsJSON=objectMapper.writeValueAsString(savedToDo);
        //WHEN+THEN
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/"+savedToDo.id()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(toDoAsJSON));
    }

}
