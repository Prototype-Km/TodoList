package com.app.todolist.layered2.service;

import com.app.todolist.layered2.dto.TodoListRequestDTO2;
import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.entity.TodoList2;
import com.app.todolist.layered2.entity.User;

import java.util.List;
import java.util.Optional;

public interface TodoListService2 {

    //일정 등록
    TodoListResponseDTO2 write(TodoListRequestDTO2 todoListRequestDTO);

    //전체 조회 //page
    List<TodoListResponseDTO2> getList();

    //하나 조회
    TodoListResponseDTO2 read(Long id);
    //null 처리
    TodoList2 findTodoByIdOrElseThrow(Long id);

    //수정하기
    TodoListResponseDTO2 update(Long id, String inputPassword, String writer, String contents);

    //일정삭제
    void deleteTodoList(Long id, String inputPassword);

    //비밀번호 검증 (회원)
    Optional<User> findByUserPassword(Long id, String inputPassword);
}
