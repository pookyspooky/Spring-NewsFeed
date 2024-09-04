package com.sparta.springnewsfeed.domain.profile.dto.response;

import lombok.Getter;

@Getter
public class UpdateProfileResponseDto {
    private Long profileId;
    private String description;

    public UpdateProfileResponseDto(Long profileId, String description){
        this.profileId = profileId;
        this.description = description;
    }
}
