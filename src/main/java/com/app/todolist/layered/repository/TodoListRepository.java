package com.app.todolist.layered.repository;

import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;

public interface TodoListRepository {

    //일정 생성
    TodoListResponseDTO save(TodoList todoList);

    //전체 일정 조회 (등록된 일정 불러오기)

    //일정 전체 조회
    /**
     *
     * **전체 일정 조회(등록된 일정 불러오기)**
     *     다음 **조건**을 바탕으로 등록된 일정 목록을 전부 조회
*          `수정일` (형식 : YYYY-MM-DD)
*        `  작성자명`
     *      조건 중 한 가지만을 충족하거나, 둘 다 충족을 하지 않을 수도, 두 가지를 모두 충족할 수도 있습니다.
     *     -수정일` 기준 내림차순으로 정렬하여 조회
 *     - **View를 생각해보자면…! (화면 구현하는 것은 요구사항이 아닙니다!)**
     *
 * **   선택 일정 조회(선택한 일정 정보 불러오기)**
     *      선택한 일정 단건의 정보를 조회할 수 있습니다.
     *      일정의 고유 식별자(ID)를 사용하여 조회합니다
     */
}
