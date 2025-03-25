package com.app.todolist.layered2.repository;

import com.app.todolist.layered2.dto.UserRequestDTO;
import com.app.todolist.layered2.dto.UserResponseDTO;
import com.app.todolist.layered2.entity.User;

import java.util.Optional;

public interface UserRepository {

    //회원 가입
    UserResponseDTO insert(User user);

    //회원 로그인
    Optional<User> findByUserEmailAndPassword(String userEmail,String userPassword);

}
