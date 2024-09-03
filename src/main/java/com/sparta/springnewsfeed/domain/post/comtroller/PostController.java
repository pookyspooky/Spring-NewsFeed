package com.sparta.springnewsfeed.domain.post.comtroller;

import com.sparta.springnewsfeed.domain.post.dto.PagedResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseListDto;
import com.sparta.springnewsfeed.domain.post.service.PostService;
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
     * 게시물 생성(유저 정보 추가 필요)
     * @param requestDto
//     * @param request
     * @return 상태 코드 201, 생성된 게시물 정보
     */
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto /**, HttpServletRequest request**/){
//        Long userId = (Long) request.getAttribute("userId");        // 필터 구현하고 나서 주석해제
        PostResponseDto createPost = postService.createPost(requestDto);
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
     * @param id
     * @return 상태 코드 200, 게시물 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id){
        PostResponseDto post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    /**
     * 게시물 수정(유저 확인 작업 추가필요)
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        PostResponseDto updatePost = postService.updatePost(id, requestDto);
        return ResponseEntity.ok(updatePost);
    }

    /**
     * 게시물 삭제(유저 확인 작업 추가필요)
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePost(@PathVariable Long id){
        Long deletePostId = postService.deletePost(id);
        return ResponseEntity.ok(deletePostId);
    }
}
