package com.app.todolist.layered2.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//페이징은 따라했습니다.
public class Paging {
    private final int currentPage;     // 현재 페이지
    private final int pageSize;        // 한 페이지당 글 수
    private final int totalCount;      // 전체 게시물 수
    private final int totalPages;      // 전체 페이지 수
    private final int offset;          // LIMIT OFFSET용

    private final int startPage;       // 시작 페이지 번호
    private final int endPage;         // 끝 페이지 번호
    private final boolean hasPrevious; // 이전 페이지 존재 여부
    private final boolean hasNext;     // 다음 페이지 존재 여부

    private static final int PAGE_BLOCK_COUNT = 10; // 한 화면에 보일 페이지 개수

    public Paging(int currentPage, int pageSize, int totalCount) {
        this.currentPage = currentPage < 1 ? 1 : currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
        this.offset = (this.currentPage - 1) * pageSize;

        // 페이지 블럭 계산
        int tempEndPage = ((int) Math.ceil((double) this.currentPage / PAGE_BLOCK_COUNT)) * PAGE_BLOCK_COUNT;
        this.startPage = tempEndPage - (PAGE_BLOCK_COUNT - 1);
        this.endPage = Math.min(tempEndPage, totalPages);

        this.hasPrevious = startPage > 1;
        this.hasNext = endPage < totalPages;
    }

    public int getRealEndPage() {
        return totalPages;
    }
}
