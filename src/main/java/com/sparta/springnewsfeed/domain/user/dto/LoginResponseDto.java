package com.sparta.springnewsfeed.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String Token;

    public LoginResponseDto(String token){
        this.Token = token;
    }
}