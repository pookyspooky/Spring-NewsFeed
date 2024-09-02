package com.sparta.springnewsfeed.controller;

import com.sparta.springnewsfeed.dto.UserRequestDto;
import com.sparta.springnewsfeed.dto.UserResponseDto;
import com.sparta.springnewsfeed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequest) {
        return ResponseEntity.ok(userService.signup(userRequest));
    }
}
