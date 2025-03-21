package com.app.todolist.layered.service;

import com.app.todolist.layered.dto.TodoListRequestDTO;
import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;
import com.app.todolist.layered.repository.TodoListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    //생성자 주입
    public TodoListServiceImpl(TodoListRepository todoListRepository){
        this.todoListRepository = todoListRepository;
    }

    @Override
    public TodoListResponseDTO write(TodoListRequestDTO todoListRequestDTO) {
       TodoList todoList = new TodoList(
                todoListRequestDTO.getWriter(),
                todoListRequestDTO.getPassword(),
                todoListRequestDTO.getContents(),
                LocalDateTime.now(),        //createdDate
                LocalDateTime.now());       //updatedDate
//            log.info(String.valueOf(todoList.getCreatedDate()));
       return todoListRepository.saveTodoList(todoList);
    }

    @Override
    public List<TodoListResponseDTO> getList() {
        return todoListRepository.findAllTodoList();
    }

    @Override
    public TodoListResponseDTO read(Long id) {
//        Optional<TodoListResponseDTO> todoList= todoListRepository.findById(id);
        TodoList selectedTodoList = findTodoByIdOrElseThrow(id);
     //ReponseDTO로 바꾸면서 password빼기.
        log.info(selectedTodoList.getPassword());
        TodoListResponseDTO dto = TodoListResponseDTO.toDTO(selectedTodoList);
        log.info(String.valueOf(dto));
        return TodoListResponseDTO.toDTO(selectedTodoList);
    }

    @Override
    public TodoList findTodoByIdOrElseThrow(Long id) {
        return todoListRepository.findTodoByIdOrElseThrow(id);
    }


    @Transactional
    @Override
    public TodoListResponseDTO update(Long id,String writer ,String inputPassword, String contents) {
        log.info("=====update=====");
        // 비밀번호 검증 / 일정검증
        log.info(inputPassword);
        TodoList todoList = validatePassword(id, inputPassword);
        log.info("입력 비밀번호: {}", inputPassword);
        log.info("DB 비밀번호: {}", todoList.getPassword());

        TodoList updateTodo = new TodoList(
                todoList.getId(),
                writer,
                todoList.getPassword(),
                contents,
                todoList.getCreatedDate(),
                LocalDateTime.now()
        );
    int updatedRaw=todoListRepository.updateWriterOrContents(updateTodo.getId(),updateTodo.getWriter(),updateTodo.getContents());
        log.info("입력 비밀번호: {}", inputPassword);
        log.info("DB 비밀번호: {}", todoList.getPassword());
    //일정 수정
    //에러발생
    if(updatedRaw == 0){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "일정 수정 실패");
    }
    //수정된 일정 다시 조회
    TodoList updatedTodo = findTodoByIdOrElseThrow(id);
    //TodoList -> toDTO로 바꾸기
    log.info("입력 비밀번호: {}", inputPassword);
    log.info("DB 비밀번호: {}", todoList.getPassword());
    return TodoListResponseDTO.toDTO(updatedTodo);
    }


    @Transactional
    @Override
    public void deleteTodoList(Long id,String inputPassword) {

        validatePassword(id,inputPassword);

        int deleteRow = todoListRepository.deleteTodoList(id);

        if(deleteRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id ="+id);
        }
    }

    //비밀번호 검증
    private TodoList validatePassword(Long id, String inputPassword){
        TodoList existingTodo = todoListRepository.findTodoByIdOrElseThrow(id);
        log.info("validatePassword");
        if(!existingTodo.getPassword().equals(inputPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return existingTodo;
    }


}
