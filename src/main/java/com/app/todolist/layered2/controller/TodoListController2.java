package com.app.todolist.layered2.controller;


import com.app.todolist.layered2.dto.*;
import com.app.todolist.layered2.service.TodoListService2;
import com.app.todolist.layered2.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/todo-list2")
public class TodoListController2 {

    //서비스  생성자주입
    private final TodoListService2 todoListService;
    private final HttpSession session;

    public TodoListController2(TodoListService2 todoListService,HttpSession session){
        this.todoListService = todoListService;
        this.session = session;
    }

    //일정 생성
    @PostMapping
    public ResponseEntity<TodoListResponseDTO2> write(@RequestBody @Valid TodoListRequestDTO2 todoListRequestDTO){
        Long userId = (Long)session.getAttribute("loginUser");
        if(userId == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인을 해주세요.");
        }
        return ResponseEntity.ok(todoListService.write(userId, todoListRequestDTO));
    }

    //일정 조회 (전체조회)
    @GetMapping
    public List<TodoListResponseDTO2> getList(){
        return todoListService.getList();
    }

//  유저 id로 전체 일정 조회
//  작성자의 고유 식별자를 통해 일정이 검색이 될 수 있도록 전체 일정 조회 코드
    @GetMapping("/user/{userId}")
    public List<TodoListResponseDTO2> findAllByUserId(@PathVariable Long userId){
         log.info("userId로 조회");
         return todoListService.findAllByUserId(userId);
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
            @RequestBody TodoListRequestDTO2  todoListRequestDTO)
    {
        //수정할 id
        TodoListResponseDTO2 updated = todoListService.update(
                id,
                todoListRequestDTO.getUserPassword(),
                todoListRequestDTO.getContents()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,@RequestBody TodoListRequestDTO2 todoListRequestDTO){
        todoListService.deleteTodoList(id,todoListRequestDTO.getUserPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //일정 전체조회(Page)  //페이징은 따라했습니다.
    @PostMapping("/page")
    public ResponseEntity<PagingResponseDTO<TodoListResponseDTO2>> getListWithSearch(@RequestBody TodoListPageRequestDTO request) {
        // 1. 전체 개수 조회 (조건 포함)
        int totalCount = todoListService.countWithCondition(request);

        // 2. 페이징 객체 생성
        Paging paging = new Paging(request.getPage(), request.getSize(), totalCount);

        // 3. 데이터 조회
        List<TodoListResponseDTO2> todos = todoListService.findWithConditionAndPaging(request, paging);

        // 4. 응답 생성
        return ResponseEntity.ok(
                PagingResponseDTO.<TodoListResponseDTO2>builder()
                        .currentPage(paging.getCurrentPage())
                        .pageSize(paging.getPageSize())
                        .totalCount(paging.getTotalCount())
                        .totalPages(paging.getTotalPages())
                        .startPage(paging.getStartPage())
                        .endPage(paging.getEndPage())
                        .hasPrevious(paging.isHasPrevious())
                        .hasNext(paging.isHasNext())
                        .data(todos)
                        .build()
        );
    }
}