package com.app.todolist.layered.controller;


import com.app.todolist.layered.dto.TodoListRequestDTO;
import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.service.TodoListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/todo-list")
public class TodoListController {


    //서비스 주입 생성자주입
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService){
        this.todoListService = todoListService;
    }

    //일정 생성
    @PostMapping
    public ResponseEntity<TodoListResponseDTO> write(@RequestBody TodoListRequestDTO todoListRequestDTO){

        return  new ResponseEntity<>(todoListService.write(todoListRequestDTO), HttpStatus.CREATED);
    }

}
