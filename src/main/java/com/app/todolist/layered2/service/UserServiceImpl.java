package com.app.todolist.layered2.service;

import com.app.todolist.layered2.dto.UserRequestDTO;
import com.app.todolist.layered2.dto.UserResponseDTO;
import com.app.todolist.layered2.entity.User;
import com.app.todolist.layered2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO join(UserRequestDTO userRequestDTO) {
//        UserRequestDTO → User
        User user = User.builder()
                .userName(userRequestDTO.getUserName())
                .userEmail(userRequestDTO.getUserEmail())
                .userPassword(userRequestDTO.getUserPassword())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        return userRepository.insert(user);
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        // 이메일 + 비밀번호로 유저 조회
        User user = userRepository.findByUserEmailAndPassword(userRequestDTO.getUserEmail(), userRequestDTO.getUserPassword())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인에 실패하셨습니다. 다시 시도해주세요"));
        return UserResponseDTO.toDTO(user); // DTO로 변환
    }

    @Override
    public List<UserResponseDTO> getList() {
        return userRepository.findAll();
    }


    //비번검증
    @Override
    public User validatePassword(Long userId, String inputPassword) {
        User user = userRepository.findByIdOrElseThrow(userId);
        if(!user.getUserPassword().equals(inputPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"다시 입력해주세요. ");
        }
        return user;
    }
}
