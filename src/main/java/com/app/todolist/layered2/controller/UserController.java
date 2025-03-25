package com.app.todolist.layered2.controller;


import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.dto.UserRequestDTO;
import com.app.todolist.layered2.dto.UserResponseDTO;
import com.app.todolist.layered2.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    //생성자 주입
    public UserController(UserService userService){
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<UserResponseDTO> join(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.join(userRequestDTO), HttpStatus.OK);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.login(userRequestDTO),HttpStatus.OK);
    }
}


