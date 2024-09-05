package com.sparta.springnewsfeed.domain.post.repository;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByModifiedAtDesc(Pageable pageable);

    Page<Post> findByUserIdInOrderByModifiedAtDesc(List<Long> userId, Pageable pageable);

    Page<Post> findByTitleContainingOrderByModifiedAtDesc(String keyword, Pageable pageable);

    Page<Post> findByContentContainingOrderByModifiedAtDesc(String keyword, Pageable pageable);
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% ORDER BY p.modifiedAt DESC")
    Page<Post> searchByTitleOrContent(String keyword, Pageable pageable);
}
