package com.app.todolist.layered2.dto;


import com.app.todolist.layered2.entity.TodoList2;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;


@Getter
@Builder
public class TodoListResponseDTO2 {
    private Long id;
    private Long userId;
    private String userName;
    private String userEmail;
    private String contents;
    private String createdDate;
    private String updatedDate;


    //toDTO
    public static TodoListResponseDTO2 toDTO(TodoList2 todoList, String userName, String userEmail){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return builder()
                .id(todoList.getId())
                .userId(todoList.getUserId())
                .userName(userName)
                .userEmail(userEmail)
                .contents(todoList.getContents())
                .createdDate(todoList.getCreatedDate().format(formatter))
                .updatedDate(todoList.getUpdatedDate().format(formatter))
                .build();
    }

}
