package com.app.todolist.layered2.service;

import com.app.todolist.layered.entity.TodoList;
import com.app.todolist.layered2.dto.Paging;
import com.app.todolist.layered2.dto.TodoListPageRequestDTO;
import com.app.todolist.layered2.dto.TodoListRequestDTO2;
import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.entity.TodoList2;
import com.app.todolist.layered2.entity.User;
import com.app.todolist.layered2.repository.TodoListRepository2;
import com.app.todolist.layered2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoListServiceImpl2 implements TodoListService2 {

    private final UserRepository userRepository;
    private final TodoListRepository2 todoListRepository;
    //생성자 주입
    public TodoListServiceImpl2(TodoListRepository2 todoListRepository,UserRepository userRepository){
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }

    //회원 id검사
    @Override
    public TodoListResponseDTO2 write(Long userId,TodoListRequestDTO2 todoListRequestDTO) {

        TodoList2 todoList2 = TodoList2.builder()
                .userId(userId)
                .contents(todoListRequestDTO.getContents())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
       return todoListRepository.saveTodoList(todoList2);
    }

    //전체보기
    @Override
    public List<TodoListResponseDTO2> getList() {
        return todoListRepository.findAllTodoList();
    }


    //전체보기 (유저 id)
    @Override
    public List<TodoListResponseDTO2> findAllByUserId(Long userId) {
        return todoListRepository.findAllByUserId(userId);
    }

    @Override
    public List<TodoListResponseDTO2> findWithConditionAndPaging(TodoListPageRequestDTO request, Paging paging) {
        return todoListRepository.findWithConditionAndPaging(request,paging);
    }

    @Override
    public int countWithCondition(TodoListPageRequestDTO requestDTO) {
        return todoListRepository.countWithCondition(requestDTO);
    }

    @Override
    public TodoListResponseDTO2 read(Long id) {
        return  todoListRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "다시 시도해주세요"));

    }

    @Override
    public TodoList2 findTodoByIdOrElseThrow(Long id) {
        return todoListRepository.findTodoByIdOrElseThrow(id);
    }

    @Transactional
    @Override
    public TodoListResponseDTO2 update(Long id, String inputPassword, String contents) {

        log.info("service");
        //일정 있는지 조회
        TodoList2 todoList2 = todoListRepository.findTodoByIdOrElseThrow(id);
        log.info("service");

        //회원Id조회
        User user = userRepository.findByIdOrElseThrow(todoList2.getUserId());

        log.info("====수정 비번 검증 ");
        log.info("입력 비번: {}", inputPassword);
        log.info("DB n비번: {}", user.getUserPassword());
        // 비밀번호 검증
        if(!user.getUserPassword().equals(inputPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"content are required values.");
        }
        log.info(todoList2.getContents());
        log.info(contents);
        //수정
        int i = todoListRepository.updateWriterOrContents(id, contents);
        log.info("수정");

        if(i == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"다시 시도해주세요");
        }
        //다시 반환
        log.info(todoList2.getContents());
        return todoListRepository.findById(id)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "조회 실패"));
    }


    @Transactional
    @Override
    public void deleteTodoList(Long id,String inputPassword) {

        TodoList2 todo = todoListRepository.findTodoByIdOrElseThrow(id);
        validateUserPassword(todo.getUserId(), inputPassword);
        int deleteRow = todoListRepository.deleteTodoList(id);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }

    }
    /**
     * 내부에 비밀번호 검증 메서드 구현
     */
    private void validateUserPassword(Long userId, String inputPassword) {
        // DB에서 User 조회
        User user = userRepository.findByIdOrElseThrow(userId);

        // 평문 비교(실무에서는 해시 비교 추천)
        if (!user.getUserPassword().equals(inputPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
    }


}
