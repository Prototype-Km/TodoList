package com.app.todolist.layered2.controller;


import com.app.todolist.layered2.dto.TodoListRequestDTO2;
import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.service.TodoListService2;
import com.app.todolist.layered2.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/todo-list2")
public class TodoListController2 {

    //서비스  생성자주입
    private final TodoListService2 todoListService;
    private final UserService userService;

    public TodoListController2(TodoListService2 todoListService,UserService userService){
        this.todoListService = todoListService;
        this.userService = userService;
    }

    //일정 생성
    @PostMapping
    public ResponseEntity<TodoListResponseDTO2> write(@RequestBody @Valid TodoListRequestDTO2 todoListRequestDTO){
        return  new ResponseEntity<>(todoListService.write(todoListRequestDTO), HttpStatus.OK);
    }

    //일정 조회 (전체조회)
    @GetMapping
    public List<TodoListResponseDTO2> getList(){
        return todoListService.getList();
    }

    //상세보기
    @GetMapping("/{id}")
    public ResponseEntity<TodoListResponseDTO2> read(@PathVariable Long id){
        return new ResponseEntity<>(todoListService.read(id), HttpStatus.OK);
    }

    //일정 수정하기 (할일 또는 작성자명 만 수정가능, 비밀번호 함께 받음)
    @PatchMapping("/{id}")
    public ResponseEntity<TodoListResponseDTO2> update(
            @PathVariable Long id,
            @RequestBody TodoListRequestDTO2 todoListRequestDTO)
    {

//        log.info(todoListRequestDTO.getPassword());
        log.info("controller");
//        return new ResponseEntity<>(todoListService.update(id,todoListRequestDTO.getWriter(),todoListRequestDTO.getPassword(),todoListRequestDTO.getContents()),HttpStatus.OK);
        return null;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,@RequestBody TodoListRequestDTO2 todoListRequestDTO){
//        todoListService.deleteTodoList(id,todoListRequestDTO.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}