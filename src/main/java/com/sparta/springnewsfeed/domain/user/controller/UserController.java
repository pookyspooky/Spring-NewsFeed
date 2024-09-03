package com.sparta.springnewsfeed.domain.user.controller;

import com.sparta.springnewsfeed.domain.user.dto.LoginResponseDto;
import com.sparta.springnewsfeed.domain.user.service.UserService;
import com.sparta.springnewsfeed.domain.user.dto.LoginRequestDto;
import com.sparta.springnewsfeed.domain.user.dto.UserRequestDto;
import com.sparta.springnewsfeed.domain.user.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
        public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequest, HttpServletResponse res) {
        return ResponseEntity.ok(userService.signup(userRequest,res));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto logInRequest, HttpServletResponse res) {
        return ResponseEntity.ok(userService.login(logInRequest,res));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,@RequestBody LoginRequestDto logInRequest) {
        return ResponseEntity.ok(userService.delete(id,logInRequest));
    }
}
