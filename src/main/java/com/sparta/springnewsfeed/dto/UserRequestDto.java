package com.sparta.springnewsfeed.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    @Email
    private String email;
    private String password;
}
