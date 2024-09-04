package com.sparta.springnewsfeed.domain.likes.repository;

import com.sparta.springnewsfeed.domain.likes.entity.Likes;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findByUserAndPost(User user, Post post);
}
