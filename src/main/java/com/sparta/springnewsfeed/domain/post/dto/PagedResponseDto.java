package com.sparta.springnewsfeed.domain.post.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PagedResponseDto<T> {
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private Boolean last;
    private Boolean first;
    private Boolean empty;
    private Boolean hasPrevious;     // 이전 버튼
    private Boolean hasNext;     // 다음 버튼

    public PagedResponseDto(Page<T> page){
        this.content = page.getContent();
        this.pageNo = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.last = page.isLast();
        this.first = page.isFirst();
        this.empty = page.isEmpty();
        this.hasPrevious = page.hasPrevious();
        this.hasNext = page.hasNext();
    }
}
