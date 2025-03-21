package com.app.todolist.layered.dto;

import com.app.todolist.layered.entity.TodoList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class TodoListResponseDTO {
    private Long id;
    private String writer;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

//    생성자? toDTO
    public TodoListResponseDTO(TodoList todoList){
        this.id = todoList.getId();
        this.writer = todoList.getWriter();
        this.contents = todoList.getContents();
        this.createDate = todoList.getCreateDate();
        this.updateDate = todoList.getUpdateDate();
    }


}
