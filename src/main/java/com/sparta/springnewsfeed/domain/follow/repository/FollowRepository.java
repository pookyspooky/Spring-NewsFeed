package com.sparta.springnewsfeed.domain.follow.repository;

import com.sparta.springnewsfeed.domain.follow.entity.Follow;
import com.sparta.springnewsfeed.domain.follow.service.CheckingAccepted;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByUser_IdAndFollowing_Id(long userId, long followingId);

    List<Follow> findAllByFollowing_IdAndAccepted(long userId, CheckingAccepted checkingAccepted);
}
