package com.sparta.springnewsfeed.domain.likes.repository;

import com.sparta.springnewsfeed.domain.likes.entity.ProfileLikes;
import com.sparta.springnewsfeed.domain.profile.entity.Profile;
import com.sparta.springnewsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileLikesRepository extends JpaRepository<ProfileLikes, Long> {
    ProfileLikes findByUserAndProfile(User user, Profile profile);
}
