package com.app.todolist.layered.dto;

import com.app.todolist.layered.entity.TodoList;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class TodoListResponseDTO {
    private Long id;
    private String writer;
    private String contents;
    private String createdDate;
    private String updatedDate;


//  toDTO
    public static TodoListResponseDTO toDTO(TodoList todoList){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return TodoListResponseDTO.builder()
                .id(todoList.getId())
                .writer(todoList.getWriter())
                .contents(todoList.getContents())
                .createdDate(todoList.getCreatedDate().format(formatter))  
                .updatedDate(todoList.getUpdatedDate().format(formatter))
                .build();
    }


}
