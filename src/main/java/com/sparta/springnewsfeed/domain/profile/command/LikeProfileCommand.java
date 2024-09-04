package com.sparta.springnewsfeed.domain.profile.command;

import com.sparta.springnewsfeed.domain.likes.entity.ProfileLikes;
import com.sparta.springnewsfeed.domain.likes.repository.ProfileLikesRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.profile.entity.Profile;

public class LikeProfileCommand implements Command{
    private ProfileLikesRepository profileLikesRepository;
    private User user;
    private Profile profile;
    private ProfileLikes profileLikes;

    public LikeProfileCommand(ProfileLikesRepository profileLikesRepository, User user, Profile profile, ProfileLikes profileLikes) {
        this.profileLikesRepository = profileLikesRepository;
        this.user = user;
        this.profile = profile;
        this.profileLikes = profileLikes;
    }

    @Override
    public void execute() {
        profileLikes = ProfileLikes.builder()
                .user(user)
                .profile(profile)
                .isLike(true)
                .build();
        profileLikesRepository.save(profileLikes);
    }

    @Override
    public void undo() {
        if (profileLikes != null){
            profileLikesRepository.delete(profileLikes);
        }
    }
}
