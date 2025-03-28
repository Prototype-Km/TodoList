package com.app.todolist.layered2.repository;

import com.app.todolist.layered2.dto.Paging;
import com.app.todolist.layered2.dto.TodoListPageRequestDTO;
import com.app.todolist.layered2.dto.TodoListRequestDTO2;
import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.entity.TodoList2;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository2 {

    //일정 생성
    TodoListResponseDTO2 saveTodoList(TodoList2 todoList);

    //일정 전체 조회 (등록된 일정 불러오기)
    List<TodoListResponseDTO2> findAllTodoList();
    List<TodoListResponseDTO2> findAllByUserId(Long userId);
    //일정 조회 (페이지포함)
    List<TodoListResponseDTO2> findWithConditionAndPaging(TodoListPageRequestDTO request, Paging paging);

    //선택 일정 조회
    Optional<TodoListResponseDTO2> findById(Long id);

    TodoList2 findTodoByIdOrElseThrow(Long id);

    //일정 수정하기
    int updateWriterOrContents(Long id,String contents);

    //삭제하기
    int deleteTodoList(Long id);

    //일정 총 갯수
    int countWithCondition(TodoListPageRequestDTO requestDTO);
}
