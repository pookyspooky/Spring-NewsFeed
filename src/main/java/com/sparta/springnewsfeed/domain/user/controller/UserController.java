package com.sparta.springnewsfeed.domain.user.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.user.dto.*;
import com.sparta.springnewsfeed.domain.user.service.UserService;
import com.sparta.springnewsfeed.global.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
        public ResponseEntity<ApiResponse<?>> signup(@RequestBody UserRequestDto userRequest, HttpServletResponse res) {
        try {
            return ResponseEntity.ok(ApiResponse.success(userService.signup(userRequest,res)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("회원 가입 중 오류가 발생햇습니다."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequestDto logInRequest, HttpServletResponse res) {
        try {
            return ResponseEntity.ok(ApiResponse.success(userService.login(logInRequest,res)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("로그인 중 오류가 발생햇습니다."));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id,@RequestBody LoginRequestDto logInRequest, @Auth AuthUser authUser) {
        try {
            return ResponseEntity.ok(ApiResponse.success(userService.delete(id,logInRequest,authUser)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("회원 삭제 중 오류가 발생햇습니다."));
        }
    }

    @PutMapping("/change/password/{id}")
    public String changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequestDto passwordRequest,@Auth AuthUser authUser) {
        return userService.changePassword(id, passwordRequest,authUser);
    }

    @GetMapping("/test")
    public void test(@Auth AuthUser authUser) {
        log.info("authUser| userName={} id={} email={}", authUser.getUserName(),authUser.getId(),authUser.getEmail());
    }
}
