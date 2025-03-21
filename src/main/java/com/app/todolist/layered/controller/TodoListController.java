package com.app.todolist.layered.controller;


import com.app.todolist.layered.dto.TodoListRequestDTO;
import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.service.TodoListService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/todo-list")
public class TodoListController {


    //서비스  생성자주입
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService){
        this.todoListService = todoListService;
    }

    //일정 생성
    @PostMapping
    public ResponseEntity<TodoListResponseDTO> write(@RequestBody TodoListRequestDTO todoListRequestDTO){
        return  new ResponseEntity<>(todoListService.write(todoListRequestDTO), HttpStatus.OK);
    }

    //일정 조회 (전체조회)
    @GetMapping
    public List<TodoListResponseDTO> getList(){
        return todoListService.getList();
    }

    //상세보기
    @GetMapping("/{id}")
    public ResponseEntity<TodoListResponseDTO> read(@PathVariable Long id){
        return new ResponseEntity<>(todoListService.read(id), HttpStatus.OK);
    }

}