package com.sparta.springnewsfeed.domain.post.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.post.dto.*;
import com.sparta.springnewsfeed.domain.post.service.PostService;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    /**
     * 게시물 생성
     * @param requestDto
     * @param authUser
     * @return 상태 코드 201, 생성된 게시물 정보
     */
    @PostMapping
    public ResponseEntity<PostCreateResponseDto> createPost(@RequestBody PostRequestDto requestDto, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        PostCreateResponseDto createPost = postService.createPost(requestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPost);
    }

    /**
     * 게시물 조회(페이징 적용)
     * @param page
     * @param size
     * @return 상태 코드 200, 게시물 정보들
     */
    @GetMapping
    public ResponseEntity<PagedResponseDto<PostResponseListDto>> getPostList(@RequestParam(defaultValue = "0")int page,
                                                                 @RequestParam(defaultValue = "10")int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<PostResponseListDto> postPage = postService.getPostList(pageable);
        return ResponseEntity.ok(new PagedResponseDto<>(postPage));
    }

    /**
     * 특정 게시물 조회
     * @param postId
     * @return 상태 코드 200, 게시물 정보
     */
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId){
        PostResponseDto post = postService.getPost(postId);
        return ResponseEntity.ok(post);
    }
    
    // 뉴스피드 게시물 조회 추가 필요

    /**
     * 게시물 수정
     * @param postId
     * @param requestDto
     * @param authUser
     * @return  상태 코드 200, 업데이트된 게시물 정보
     */
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        PostResponseDto updatePost = postService.updatePost(postId, requestDto, userId);
        return ResponseEntity.ok(updatePost);
    }

    /**
     * 게시물 삭제
     * @param postId
     * @param authUser
     * @return  상태 코드 200, 삭제된 게시물 아이디
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Long> deletePost(@PathVariable Long postId, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        Long deletePostId = postService.deletePost(postId, userId);
        return ResponseEntity.ok(deletePostId);
    }

    /**
     * 좋아요 기능
     * @param postId
     * @param authUser
     */
    @PostMapping("/{postId}/likes")
    public void toggleLikePost(@PathVariable Long postId, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        postService.toggleLikePost(postId, userId);
    }
}
