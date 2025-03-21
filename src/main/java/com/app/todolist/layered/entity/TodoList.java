package com.app.todolist.layered.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TodoList {
    private Long id;
    private final String writer;
    private final String password;
    private final String contents;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public TodoList(String writer, String password, String contents, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.writer = writer;
        this.password = password;
        this.contents = contents;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}