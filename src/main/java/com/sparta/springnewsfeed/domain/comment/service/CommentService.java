package com.sparta.springnewsfeed.domain.comment.service;

import com.sparta.springnewsfeed.domain.comment.command.Command;
import com.sparta.springnewsfeed.domain.comment.command.LikeCommentCommand;
import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.comment.repository.CommentRepository;
import com.sparta.springnewsfeed.domain.likes.entity.CommentLikes;
import com.sparta.springnewsfeed.domain.likes.entity.Likes;
import com.sparta.springnewsfeed.domain.likes.repository.CommentLikesRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.post.repository.PostRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentLikesRepository commentLikesRepository;

    // 댓글 작성
    @Transactional
    public CommentResponseDto saveComment(Long postId, Long userId, CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));
        Comment comment = new Comment(
            requestDto.getComment(),
            post,
            user
        );
        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDto.entityToDto(savedComment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));

        if (!comment.getUser().getId().equals(userId))
            throw new IllegalArgumentException("권한이 없습니다.");

        comment.update(requestDto.getComment());

        commentRepository.save(comment);

        return CommentResponseDto.entityToDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public Long deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));

        if (!comment.getUser().getId().equals(userId))
            throw new IllegalArgumentException("권한이 없습니다.");

        commentRepository.delete(comment);
        return commentId;
    }

    // 댓글 좋아요
    @Transactional
    public void toggleLikeComment(Long commentId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        CommentLikes existingLike = commentLikesRepository.findByUserAndComment(user, comment);

        if (existingLike != null) {
            Command unlikeCommand = new LikeCommentCommand(commentLikesRepository, user, comment, existingLike);
            unlikeCommand.undo();
        } else {
            Command likeCommand = new LikeCommentCommand(commentLikesRepository, user, comment, null);
            likeCommand.execute();
        }
    }
}
