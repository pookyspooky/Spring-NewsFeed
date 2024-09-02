package com.sparta.springnewsfeed.user.controller;

import com.sparta.springnewsfeed.user.dto.LoginRequestDto;
import com.sparta.springnewsfeed.user.dto.UserRequestDto;
import com.sparta.springnewsfeed.user.dto.UserResponseDto;
import com.sparta.springnewsfeed.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
        public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequest) {
        return ResponseEntity.ok(userService.signup(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto logInRequest) {
        return ResponseEntity.ok(userService.login(logInRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,@RequestBody LoginRequestDto logInRequest) {
        return ResponseEntity.ok(userService.delete(id,logInRequest));
    }
}
