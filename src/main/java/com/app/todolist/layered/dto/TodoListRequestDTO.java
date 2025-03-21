package com.app.todolist.layered.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListRequestDTO {
    private String writer;
    private String password;
    private String contents;

}
