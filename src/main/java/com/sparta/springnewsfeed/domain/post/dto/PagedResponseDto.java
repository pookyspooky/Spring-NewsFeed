package com.sparta.springnewsfeed.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.domain.Page;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PagedResponseDto<T> {
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private Boolean last;

    public PagedResponseDto(Page<T> page){
        this.content = page.getContent();
        this.pageNo = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.last = page.isLast();
    }
}
