package com.sparta.springnewsfeed.domain.comment.controller;

import com.sparta.springnewsfeed.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성

    // 댓글 수정

    // 댓글 삭제

}
