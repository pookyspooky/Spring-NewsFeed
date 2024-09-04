package com.sparta.springnewsfeed.domain.likes.repository;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.likes.entity.CommentLikes;
import com.sparta.springnewsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    CommentLikes findByUser(User user);
}
