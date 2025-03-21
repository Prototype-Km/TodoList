package com.app.todolist.layered.service;

import com.app.todolist.layered.dto.TodoListRequestDTO;
import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListService {

    //일정 등록
    TodoListResponseDTO write(TodoListRequestDTO todoListRequestDTO);

    //전체 조회 //page
    List<TodoListResponseDTO> getList();

    //하나 조회
    TodoListResponseDTO read(Long id);
    //null 처리
    TodoList findTodoByIdOrElseThrow(Long id);

    //수정하기
    TodoListResponseDTO update(Long id,String inputPassword,String writer, String contents);
    //일정삭제
 void deleteTodoList(Long id, String inputPassword);
}
