package com.sparta.springnewsfeed.domain.comment.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.service.CommentService;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import com.sparta.springnewsfeed.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<?>> saveComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @Auth AuthUser authUser){
        try {
            Long userId = authUser.getId();
            return ResponseEntity.ok(ApiResponse.success(commentService.saveComment(postId, userId, requestDto)));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("댓글 작성 중 오류가 발생햇습니다."));
        }
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<?>> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @Auth AuthUser authUser){
        try {
            Long userId = authUser.getId();
            return ResponseEntity.ok(ApiResponse.success(commentService.updateComment(commentId, requestDto, userId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("댓글 수정 중 오류가 발생햇습니다."));
        }
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<?>> deleteComment(@PathVariable Long commentId, @Auth AuthUser authUser){
        try {
            Long userId = authUser.getId();
            return ResponseEntity.ok(ApiResponse.success(commentService.deleteComment(commentId, userId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("댓글 삭제 중 오류가 발생햇습니다."));
        }
    }

    // 댓글 좋아요
    @PostMapping("/comments/{commentId}/likes")
    public void toggleLikeComment(@PathVariable Long commentId, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        commentService.toggleLikeComment(commentId, userId);
    }
}
