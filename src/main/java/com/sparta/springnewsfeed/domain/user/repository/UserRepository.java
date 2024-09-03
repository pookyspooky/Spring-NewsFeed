package com.sparta.springnewsfeed.domain.user.repository;

import com.sparta.springnewsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


    Optional<User>findByUsername(String subject);

    Optional<User> findById(Long userId);
}
