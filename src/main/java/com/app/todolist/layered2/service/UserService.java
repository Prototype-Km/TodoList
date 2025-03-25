package com.app.todolist.layered2.service;


import com.app.todolist.layered2.dto.UserRequestDTO;
import com.app.todolist.layered2.dto.UserResponseDTO;
import com.app.todolist.layered2.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    //회원가입
    UserResponseDTO join(UserRequestDTO userRequestDTO);
    //로그인  //인증
    UserResponseDTO login(UserRequestDTO userRequestDTO);


    //수정

    //삭제

}
