package com.app.todolist.layered.service;

import com.app.todolist.layered.dto.TodoListRequestDTO;
import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;
import com.app.todolist.layered.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    //생성자 주입
    public TodoListServiceImpl(TodoListRepository todoListRepository){
        this.todoListRepository = todoListRepository;
    }

    @Override
    public TodoListResponseDTO write(TodoListRequestDTO todoListRequestDTO) {
       TodoList todoList =  new TodoList(
                todoListRequestDTO.getWriter(),
                todoListRequestDTO.getPassword(),
                todoListRequestDTO.getContents(),
                LocalDateTime.now(),
                LocalDateTime.now());
       return todoListRepository.save(todoList);
    }


}
