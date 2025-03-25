package com.app.todolist.layered2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListRequestDTO2 {
    private Long id;
    private Long userId;
    private String userPassword;

    @NotBlank(message = "할일을 등록해주세요 .")
    @Size(max = 200, message = "할 일은 200자 이하로 입력해주세요.")
    private String contents;
}
