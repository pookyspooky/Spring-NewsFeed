package com.sparta.springnewsfeed.domain.profile.dto.response;

import com.sparta.springnewsfeed.domain.profile.entity.Profile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateProfileResponseDto {
    private Long id;
    private String username;
    private String email;
    private String description;
    private LocalDateTime createdAt ;

    public CreateProfileResponseDto(Profile profile){
        this.id = profile.getId();
        this.username = profile.getUser().getUsername();
        this.email = profile.getUser().getEmail();
        this.description = profile.getDescription();
        this.createdAt = profile.getCreatedAt();
    }

}
