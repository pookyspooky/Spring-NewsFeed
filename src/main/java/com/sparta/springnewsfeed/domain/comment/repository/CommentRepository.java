package com.sparta.springnewsfeed.domain.comment.repository;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
