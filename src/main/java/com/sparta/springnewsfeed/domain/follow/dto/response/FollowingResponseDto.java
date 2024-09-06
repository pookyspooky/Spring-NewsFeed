package com.sparta.springnewsfeed.domain.follow.dto.response;

import com.sparta.springnewsfeed.domain.follow.entity.Follow;
import lombok.Getter;

@Getter
public class FollowingResponseDto {

    private long id;
    private long followingId;
    private String followingName;
    private String checkingAccepted;

    public FollowingResponseDto(Follow follow) {
        this.id = follow.getId();
        this.followingId = follow.getFollowing().getId();
        this.followingName = follow.getFollowing().getUsername();
        this.checkingAccepted = follow.getAccepted().toString();
    }

}
