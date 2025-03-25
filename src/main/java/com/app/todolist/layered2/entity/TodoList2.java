package com.app.todolist.layered2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoList2 {
    private Long id;
    private Long userId;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


    public TodoList2(Long userId, String contents, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.userId = userId;
        this.contents = contents;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}
