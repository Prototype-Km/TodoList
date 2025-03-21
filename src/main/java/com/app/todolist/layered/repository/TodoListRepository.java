package com.app.todolist.layered.repository;

import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository {

    //일정 생성
    TodoListResponseDTO saveTodoList(TodoList todoList);

    //일정 전체 조회 (등록된 일정 불러오기)
    List<TodoListResponseDTO> findAllTodoList();
    //선택 일정 조회
    Optional<TodoListResponseDTO> findById(Long id);
    TodoList findTodoByIdOrElseThrow(Long id);

    //일정 수정하기
    int updateWriterOrContents(Long id,String writer,String contents);

    //삭제하기
    int deleteTodoList(Long id);
}
