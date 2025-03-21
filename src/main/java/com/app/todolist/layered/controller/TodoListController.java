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

    //일정 수정하기 (할일 또는 작성자명 만 수정가능, 비밀번호 함께 받음)
    @PatchMapping("/{id}")
    public ResponseEntity<TodoListResponseDTO> update(
            @PathVariable Long id,
            @RequestBody TodoListRequestDTO todoListRequestDTO)
    {

        log.info(todoListRequestDTO.getPassword());
        log.info("controller");
        return new ResponseEntity<>(todoListService.update(id,todoListRequestDTO.getWriter(),todoListRequestDTO.getPassword(),todoListRequestDTO.getContents()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,@RequestBody TodoListRequestDTO todoListRequestDTO){
        todoListService.deleteTodoList(id,todoListRequestDTO.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}