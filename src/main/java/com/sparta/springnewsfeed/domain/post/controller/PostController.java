package com.sparta.springnewsfeed.domain.post.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.post.dto.PagedResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseListDto;
import com.sparta.springnewsfeed.domain.post.service.PostService;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import com.sparta.springnewsfeed.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;

    /**
     * 게시물 생성
     * @param requestDto
     * @param authUser
     * @return 상태 코드 201, 생성된 게시물 정보
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> createPost(@RequestPart(value = "image", required = false) MultipartFile image,
                                                     @RequestPart @Valid PostRequestDto requestDto,
                                                     @Auth AuthUser authUser){
        try {
            Long userId = authUser.getId();

            // image가 존재하고 비어있지 않다면 requestDto에 설정
            if (image != null && !image.isEmpty()) {
                requestDto.setImage(image);
            }

            PostResponseDto createPost = postService.createPost(requestDto, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(createPost));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("게시물 생성 중 오류가 발생했습니다."));
        }

    }

    /**
     * 게시물 조회(페이징 적용)
     * @param page
     * @param size
     * @return 상태 코드 200, 게시물 정보들
     */
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPostList(@RequestParam(defaultValue = "0")int page,
                                                                 @RequestParam(defaultValue = "10")int size){
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<PostResponseListDto> postPage = postService.getPostList(pageable);
            return ResponseEntity.ok(ApiResponse.success(new PagedResponseDto<>(postPage)));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("게시물 목록 조회 중 오류가 발생햇습니다."));
        }
    }

    /**
     * 특정 게시물 조회
     * @param postId
     * @return 상태 코드 200, 게시물 정보
     */
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> getPost(@PathVariable Long postId){
        try {
            PostResponseDto post = postService.getPost(postId);
            return ResponseEntity.ok(ApiResponse.success(post));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("특정 게시물 목록 조회 중 오류가 발생햇습니다."));
        }
    }

    /**
     * 뉴스피드 게시물 조회
     * @param page
     * @param size
     * @param authUser
     * @return 상태 코드 200, 뉴스피드 게시물 정보들
     */
    @GetMapping("/newsfeed")
    public ResponseEntity<ApiResponse<?>> getNewsfeed(@RequestParam(defaultValue = "0")int  page,
                                                                             @RequestParam(defaultValue = "10")int size,
                                                                             @Auth AuthUser authUser){
        try {
            Pageable pageable = PageRequest.of(page, size);
            Long userId = authUser.getId();
            Page<PostResponseListDto> newsfeedPage = postService.getNewsfeed(userId, pageable);
            return ResponseEntity.ok(ApiResponse.success(new PagedResponseDto<>(newsfeedPage)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("뉴스피드 게시물 조회 중 오류가 발생햇습니다."));
        }
    }

    /**
     * 게시물 수정
     * @param postId
     * @param requestDto
     * @param authUser
     * @return  상태 코드 200, 업데이트된 게시물 정보
     */
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @Auth AuthUser authUser){
        try {
            Long userId = authUser.getId();
            PostResponseDto updatePost = postService.updatePost(postId, requestDto, userId);
            return ResponseEntity.ok(ApiResponse.success(updatePost));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("게시물 수정 중 오류가 발생햇습니다."));
        }
    }

    /**
     * 게시물 삭제
     * @param postId
     * @param authUser
     * @return  상태 코드 200, 삭제된 게시물 아이디
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> deletePost(@PathVariable Long postId, @Auth AuthUser authUser){
        try {
            Long userId = authUser.getId();
            Long deletePostId = postService.deletePost(postId, userId);
            return ResponseEntity.ok(ApiResponse.success(deletePostId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("게시물 삭제 중 오류가 발생햇습니다."));
        }
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

    /**
     * 검색 기능
     * @param keyword
     * @param searchType
     * @param page
     * @param size
     * @return 상태 코드 200, 검색 결과 게시물들
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchPosts(@RequestParam String keyword,
                                                                             @RequestParam(defaultValue = "both") String searchType,
                                                                             @RequestParam(defaultValue = "0")int page,
                                                                             @RequestParam(defaultValue = "10")int size){
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<PostResponseListDto> searchResult = postService.searchPosts(keyword, searchType, pageable);

            return ResponseEntity.ok(ApiResponse.success(new PagedResponseDto<>(searchResult)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("검색 기능 중 오류가 발생햇습니다."));
        }
    }
}
