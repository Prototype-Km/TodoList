package com.app.todolist.layered.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class TodoList {
    private Long id;
    private final String writer;
    private final String password;
    private final String contents;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public TodoList(String writer, String password, String contents, LocalDateTime createDate, LocalDateTime updateDate) {
        this.writer = writer;
        this.password = password;
        this.contents = contents;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}