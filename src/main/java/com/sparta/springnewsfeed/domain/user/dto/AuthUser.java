package com.sparta.springnewsfeed.domain.user.dto;

import lombok.Getter;

@Getter
public class AuthUser {
    private final Long id;
    private final String userName;
    private final String email;

    public AuthUser(Long id,String userName,String email){
        this. id = id;
        this.userName = userName;
        this.email = email;
    }
}
