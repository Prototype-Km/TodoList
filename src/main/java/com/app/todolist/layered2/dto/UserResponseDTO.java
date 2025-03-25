package com.app.todolist.layered2.dto;

import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String userName;
    private String userEmail;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


    //toDTO
    public static UserResponseDTO toDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getCreatedDate())
                .build();
    }
}
