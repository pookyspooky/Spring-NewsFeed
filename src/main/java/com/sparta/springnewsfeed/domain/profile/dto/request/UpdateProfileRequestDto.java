package com.sparta.springnewsfeed.domain.profile.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProfileRequestDto {
    private String description;

    public UpdateProfileRequestDto(String description){
        this.description = description;
    }
}
