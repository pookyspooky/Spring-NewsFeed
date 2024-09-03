package com.sparta.springnewsfeed.domain.post.repository;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
