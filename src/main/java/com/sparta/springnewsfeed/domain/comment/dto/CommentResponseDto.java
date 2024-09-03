package com.sparta.springnewsfeed.domain.comment.dto;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private final Long id;
    private final String comment;
    private final LocalDateTime createdAt;

    public static CommentResponseDto entityToDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getComment(),
                comment.getCreatedAt()
        );
    }
}
