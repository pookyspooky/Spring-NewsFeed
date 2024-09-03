package com.sparta.springnewsfeed.domain.comment.controller;

import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> saveComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(commentService.saveComment(postId, userId, requestDto));
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(commentService.updateComment(commentId, requestDto, userId));
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable Long commentId, HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(commentService.deleteComment(commentId, userId));
    }
}
