package com.sparta.springnewsfeed.dto;

import com.sparta.springnewsfeed.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
