package com.app.todolist.layered2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//페이지 요청 DTO 페이징은 따라했습니다.
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListPageRequestDTO {
    private int page;
    private int size;
    private String userName;   // 작성자 이름 (선택)
    private String contents;   // 할 일 내용 (선택)
}

