package com.app.todolist.layered2.service;

import com.app.todolist.layered2.dto.TodoListRequestDTO2;
import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.entity.TodoList2;
import com.app.todolist.layered2.entity.User;
import com.app.todolist.layered2.repository.TodoListRepository2;
import lombok.extern.slf4j.Slf4j;
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

    private final TodoListRepository2 todoListRepository;
    //생성자 주입
    public TodoListServiceImpl2(TodoListRepository2 todoListRepository){
        this.todoListRepository = todoListRepository;
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
    public TodoListResponseDTO2 read(Long id) {
//        Optional<TodoListResponseDTO> todoList= todoListRepository.findById(id);
        TodoList2 selectedTodoList = findTodoByIdOrElseThrow(id);
     //ReponseDTO로 바꾸면서 password빼기.
//        log.info(selectedTodoList.getPassword());
//        TodoListResponseDTO dto = TodoListResponseDTO.toDTO(selectedTodoList);
//        log.info(String.valueOf(dto));
//        return TodoListResponseDTO.toDTO(selectedTodoList);
        return null;
    }

    @Override
    public TodoList2 findTodoByIdOrElseThrow(Long id) {
        return todoListRepository.findTodoByIdOrElseThrow(id);
    }


    @Transactional
    @Override
    public TodoListResponseDTO2 update(Long id, String writer , String inputPassword, String contents) {
        log.info(inputPassword);

        //Validation <BindingResult>?
        if(writer == null || contents == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The title and content are required values.");
        }
        // 비밀번호 검증 / 일정검증
//        TodoList todoList = validatePassword(id, inputPassword);
//
//        TodoList updateTodo = new TodoList(
//                todoList.getId(),
//                writer,
//                todoList.getPassword(),
//                contents,
//                todoList.getCreatedDate(),
//                LocalDateTime.now()
//        );

//    int updatedRaw=todoListRepository.updateWriterOrContents(updateTodo.getId(),updateTodo.getWriter(),updateTodo.getContents());
        log.info("입력 비밀번호: {}", inputPassword);
//        log.info("DB 비밀번호: {}", todoList.getPassword());

    //업데이트 실패
//    if(updatedRaw == 0){
//        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "일정 수정 실패");
//    }
    //수정된 일정 다시 조회
    TodoList2 updatedTodo = findTodoByIdOrElseThrow(id);
    //TodoList -> toDTO로 바꾸기
    log.info("입력 비밀번호: {}", inputPassword);
//    log.info("DB 비밀번호: {}", todoList.getPassword());
//    return TodoListResponseDTO.toDTO(updatedTodo);
        return null;
    }


    @Transactional
    @Override
    public void deleteTodoList(Long id,String inputPassword) {

//        validatePassword(id,inputPassword);

        int deleteRow = todoListRepository.deleteTodoList(id);

        if(deleteRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id ="+id);
        }
    }

    @Override
    public Optional<User> findByUserPassword(Long id, String inputPassword) {


        return null;

    }


    //비밀번호 검증
//    private TodoList validatePassword(Long id, String inputPassword){

//        log.info("validatePassword");
//        if(!existingTodo.getPassword().equals(inputPassword)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }
//        return existingTodo;
//    }


}
