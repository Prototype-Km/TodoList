package com.app.todolist.layered2.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Builder
public class User {
    private Long id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public User(Long id,String userName, String userEmail, String userPassword, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.createdDate =  createdDate;
        this.updatedDate =  updatedDate;
    }

}
