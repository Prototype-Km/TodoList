package com.app.todolist.layered.repository;

import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository {

    //일정 생성
    TodoListResponseDTO save(TodoList todoList);

    //일정 전체 조회 (등록된 일정 불러오기)
    List<TodoListResponseDTO> findAll();
    //선택 일정 조회
    Optional<TodoListResponseDTO> findById(Long id);

    TodoList findTodoByIdOrElseThrow(Long id);
    /**
     *
     * **전체 일정 조회(등록된 일정 불러오기)**
 * **   선택 일정 조회(선택한 일정 정보 불러오기)**
     *      선택한 일정 단건의 정보를 조회할 수 있습니다.
     *      일정의 고유 식별자(ID)를 사용하여 조회합니다
     */
}
