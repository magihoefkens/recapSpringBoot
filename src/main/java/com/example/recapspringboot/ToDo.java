package com.example.recapspringboot;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("todos")
@With
public record ToDo(
        String id,
        String description,
        ToDoStatus status
) {
}
