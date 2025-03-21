package com.app.todolist.layered.service;

import com.app.todolist.layered.dto.TodoListRequestDTO;
import com.app.todolist.layered.dto.TodoListResponseDTO;

public interface TodoListService {

    TodoListResponseDTO write(TodoListRequestDTO todoListRequestDTO);

}
