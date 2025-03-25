package com.app.todolist.layered2.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
//페이징은 따라했습니다.
public class PagingResponseDTO<T> {
    private int currentPage;     // 현재 페이지 번호
    private int pageSize;        // 한 페이지당 게시물 수
    private int totalCount;      // 전체 게시물 수
    private int totalPages;      // 총 페이지 수
    private int startPage;       // 시작 페이지 번호
    private int endPage;         // 끝 페이지 번호
    private boolean hasPrevious; // 이전 페이지 존재 여부
    private boolean hasNext;     // 다음 페이지 존재 여부
    private List<T> data;        // 실제 응답 데이터 리스트
}
