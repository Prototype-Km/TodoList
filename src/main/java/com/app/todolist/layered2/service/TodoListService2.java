package com.app.todolist.layered2.service;

import com.app.todolist.layered2.dto.Paging;
import com.app.todolist.layered2.dto.TodoListPageRequestDTO;
import com.app.todolist.layered2.dto.TodoListRequestDTO2;
import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.entity.TodoList2;
import com.app.todolist.layered2.entity.User;

import java.util.List;
import java.util.Optional;

public interface TodoListService2 {

    //일정 등록
    TodoListResponseDTO2 write(Long userId,TodoListRequestDTO2 todoListRequestDTO);
    //전체 조회
    List<TodoListResponseDTO2> getList();
    //전체 조회 //유저로 조회
    List<TodoListResponseDTO2> findAllByUserId(Long userId);
    //페이지
    List<TodoListResponseDTO2> findWithConditionAndPaging(TodoListPageRequestDTO request, Paging paging);
    int countWithCondition(TodoListPageRequestDTO requestDTO);

    //하나 조회
    TodoListResponseDTO2 read(Long id);
    //null 처리
    TodoList2 findTodoByIdOrElseThrow(Long id);
    //수정하기
    TodoListResponseDTO2 update(Long id, String inputPassword, String contents);

    //일정삭제
    void deleteTodoList(Long id, String inputPassword);

}
