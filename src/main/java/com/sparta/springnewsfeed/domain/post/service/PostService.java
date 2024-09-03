package com.sparta.springnewsfeed.domain.post.service;

import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseListDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
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
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto requestDto /**, Long userId**/) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException(USER_ERROR_MESSAGE));           // 필터 구현되고 나서 주석 해제

        Post post = requestDto.toEntity();
//        post.setUser(user);

        Post savePost = postRepository.save(post);
        return PostResponseDto.fromEntity(savePost);
    }

    public Page<PostResponseListDto> getPostList(Pageable pageable) {
        Page<Post> postPage = postRepository.findAllByOrderByModifiedAtDesc(pageable);
        return postPage.map(PostResponseListDto::fromEntity);
    }
}
