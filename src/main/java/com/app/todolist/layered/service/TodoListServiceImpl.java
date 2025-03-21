package com.app.todolist.layered.service;

import com.app.todolist.layered.dto.TodoListRequestDTO;
import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;
import com.app.todolist.layered.repository.TodoListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    //생성자 주입
    public TodoListServiceImpl(TodoListRepository todoListRepository){
        this.todoListRepository = todoListRepository;
    }

    @Override
    public TodoListResponseDTO write(TodoListRequestDTO todoListRequestDTO) {
       TodoList todoList = new TodoList(
                todoListRequestDTO.getWriter(),
                todoListRequestDTO.getPassword(),
                todoListRequestDTO.getContents(),
                LocalDateTime.now(),        //createdDate
                LocalDateTime.now());       //updatedDate
//            log.info(String.valueOf(todoList.getCreatedDate()));
       return todoListRepository.save(todoList);
    }

    @Override
    public List<TodoListResponseDTO> getList() {
        return todoListRepository.findAll();
    }

    @Override
    public TodoListResponseDTO read(Long id) {

//        Optional<TodoListResponseDTO> todoList= todoListRepository.findById(id);
        TodoList selectedTodoList = findTodoByIdOrElseThrow(id);
     //ReponseDTO로 바꾸면서 password빼기.
        return TodoListResponseDTO.toDTO(selectedTodoList);
    }

    @Override
    public TodoList findTodoByIdOrElseThrow(Long id) {
        return todoListRepository.findTodoByIdOrElseThrow(id);
    }


}
