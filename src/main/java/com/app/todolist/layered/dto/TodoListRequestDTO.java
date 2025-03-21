package com.app.todolist.layered.dto;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class TodoListRequestDTO {
    private String writer;
    private String password;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
