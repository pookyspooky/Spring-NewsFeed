package com.sparta.springnewsfeed.domain.post.service;

import com.sparta.springnewsfeed.domain.file.entity.File;
import com.sparta.springnewsfeed.domain.file.service.FileService;
import com.sparta.springnewsfeed.domain.follow.dto.response.FollowingResponseDto;
import com.sparta.springnewsfeed.domain.follow.service.FollowService;
import com.sparta.springnewsfeed.domain.likes.entity.Likes;
import com.sparta.springnewsfeed.domain.likes.repository.LikesRepository;
import com.sparta.springnewsfeed.domain.post.command.Command;
import com.sparta.springnewsfeed.domain.post.command.LikePostCommand;
import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseDto;
import com.sparta.springnewsfeed.domain.post.dto.PostResponseListDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import com.sparta.springnewsfeed.global.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private static final String USER_ERROR_MESSAGE = "유저를 찾을 수 없습니다.";
    private static final String POST_ERROR_MESSAGE = "일정을 찾을 수 없습니다.";
    private static final String UNAUTHORIZED_ERROR_MESSAGE = "권한이 없습니다.";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final FollowService followService;
    private final FileService fileService;

    // 게시물 생성
    public PostResponseDto createPost(PostRequestDto requestDto, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(USER_ERROR_MESSAGE));

        Post post = requestDto.toEntity();
        post.setUser(user);

        if (requestDto.getImage() != null && !requestDto.getImage().isEmpty()){
            File file =  fileService.saveFile(requestDto.getImage());
            post.addFile(file);
        }

        Post savePost = postRepository.save(post);
        return PostResponseDto.fromEntity(savePost);
    }
    // 게시물 목록 조회
    @Transactional(readOnly = true)
    public Page<PostResponseListDto> getPostList(Pageable pageable) {
        Page<Post> postPage = postRepository.findAllByOrderByModifiedAtDesc(pageable);
        return postPage.map(PostResponseListDto::fromEntity);
    }
    // 특정 게시물 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(POST_ERROR_MESSAGE));

        return PostResponseDto.fromEntity(post);
    }

    // 뉴스피드 게시물 조회
    @Transactional(readOnly = true)
    public Page<PostResponseListDto> getNewsfeed(Long userId, Pageable pageable) {
        // 사용자의 팔로잉 목록 가져오기
        List<FollowingResponseDto> followings = followService.getFollowingList(userId);
        List<Long> followingIds = followings.stream()
                .map(FollowingResponseDto::getFollowingId)
                .collect(Collectors.toList());

        // 팔로잉하는 사용자들의 게시물 조회
        Page<Post> newsfeedPosts = postRepository.findByUserIdInOrderByModifiedAtDesc(followingIds, pageable);

        return newsfeedPosts.map(PostResponseListDto::fromEntity);
    }

    // 게시물 수정
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

    // 게시물 삭제
    public Long deletePost(Long id, Long userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(POST_ERROR_MESSAGE));

        if (!post.getUser().getId().equals(userId))
            throw new UnauthorizedAccessException(UNAUTHORIZED_ERROR_MESSAGE);

        postRepository.delete(post);
        return id;
    }

    // 게시물 좋아요
    public void toggleLikePost(Long postId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(USER_ERROR_MESSAGE));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(POST_ERROR_MESSAGE));

        if (post.getUser().getId().equals(userId))
            throw new UnauthorizedAccessException("자기 게시물에 좋아요를 누를 수 없습니다.");

        Likes existingLike = likesRepository.findByUserAndPost(user, post);

        if (existingLike != null){
            Command unlikeCommand = new LikePostCommand(likesRepository, user, post, existingLike);
            unlikeCommand.undo();
        }else {
            Command likeCommand = new LikePostCommand(likesRepository, user, post, null);
            likeCommand.execute();
        }
    }
    // 검색 기능
    @Transactional(readOnly = true)
    public Page<PostResponseListDto> searchPosts(String keyword, String searchType, Pageable pageable){
        Page<Post> postPage;

        switch (searchType.toLowerCase()){
            case "title":
                postPage = postRepository.findByTitleContainingOrderByModifiedAtDesc(keyword, pageable);
                break;
            case "content":
                postPage = postRepository.findByContentContainingOrderByModifiedAtDesc(keyword, pageable);
                break;
            case "both":
                postPage = postRepository.searchByTitleOrContent(keyword, pageable);
                break;
            default:
                throw new IllegalArgumentException("검색 타입을 찾을 수 없습니다.");
        }
        return postPage.map(PostResponseListDto::fromEntity);
    }
}
