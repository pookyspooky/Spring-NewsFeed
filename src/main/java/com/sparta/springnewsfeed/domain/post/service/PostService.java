package com.sparta.springnewsfeed.domain.post.service;

import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseListDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import com.sparta.springnewsfeed.global.exception.UnauthorizedAccessException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
    private static final String USER_ERROR_MESSAGE = "유저를 찾을 수 없습니다.";
    private static final String POST_ERROR_MESSAGE = "일정을 찾을 수 없습니다.";
    private static final String UNAUTHORIZED_ERROR_MESSAGE = "권한이 없습니다.";
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(USER_ERROR_MESSAGE));

        Post post = requestDto.toEntity();
        post.setUser(user);

        Post savePost = postRepository.save(post);
        return PostResponseDto.fromEntity(savePost);
    }
    @Transactional(readOnly = true)
    public Page<PostResponseListDto> getPostList(Pageable pageable) {
        Page<Post> postPage = postRepository.findAllByOrderByModifiedAtDesc(pageable);
        return postPage.map(PostResponseListDto::fromEntity);
    }
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(POST_ERROR_MESSAGE));

        return PostResponseDto.fromEntity(post);
    }

    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, Long userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(POST_ERROR_MESSAGE));

        if (!post.getUser().getId().equals(userId))
            throw new UnauthorizedAccessException(UNAUTHORIZED_ERROR_MESSAGE);


        if (requestDto.hasTitle())
            post.setTitle(requestDto.getTitle());
        if (requestDto.hasContent())
            post.setContent(requestDto.getContent());

        Post updatePost = postRepository.save(post);
        return PostResponseDto.fromEntity(updatePost);
    }

    public Long deletePost(Long id, Long userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(POST_ERROR_MESSAGE));

        if (!post.getUser().getId().equals(userId))
            throw new UnauthorizedAccessException(UNAUTHORIZED_ERROR_MESSAGE);

        postRepository.delete(post);
        return id;
    }
}
