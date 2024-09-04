package com.sparta.springnewsfeed.domain.follow.dto.response;

import com.sparta.springnewsfeed.domain.follow.entity.Follow;
import lombok.Getter;

@Getter
public class FollowerResponseDto {

    private long id;
    private long followerId;
    private String followerName;
    private String checkingAccepted;

    public FollowerResponseDto(Follow follow) {
        this.id = follow.getId();
        this.followerId = follow.getFollower().getId();
        this.followerName = follow.getFollower().getUsername();
        this.checkingAccepted = follow.getAccepted().toString();
    }
}
