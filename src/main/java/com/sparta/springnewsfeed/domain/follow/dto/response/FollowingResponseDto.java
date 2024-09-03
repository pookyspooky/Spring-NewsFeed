package com.sparta.springnewsfeed.domain.follow.dto.response;

import com.sparta.springnewsfeed.domain.follow.entity.Follow;
import lombok.Getter;

@Getter
public class FollowingResponseDto {

    private long id;
    private long userId;
    private long followingId;
    private String checkingAccepted;

    public FollowingResponseDto(Follow follow) {
        this.id = follow.getId();
        this.userId = follow.getUser().getId();
        this.followingId = follow.getFollowing().getId();
        this.checkingAccepted = follow.getAccepted();
    }

}
