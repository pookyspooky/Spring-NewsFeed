package com.sparta.springnewsfeed.domain.follow.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.follow.dto.response.FollowerResponseDto;
import com.sparta.springnewsfeed.domain.follow.dto.response.FollowingResponseDto;
import com.sparta.springnewsfeed.domain.follow.service.FollowService;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import com.sparta.springnewsfeed.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 팔로우 신청
    @PostMapping("/follow/{followingId}")
    public ResponseEntity<ApiResponse<?>> followingRequest(@PathVariable long followingId, @Auth AuthUser authUser) {
        try {
            return ResponseEntity.ok(ApiResponse.success(followService.followingRequest(authUser.getId(), followingId)));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("팔로우 신청 중 오류가 발생햇습니다."));
        }
    }

    // 팔로우 신청 목록 조회
    @GetMapping("/follow/request/follower-list")
    public ResponseEntity<ApiResponse<?>> getRequestedFollowerList(@Auth AuthUser authUser) {
        try {
            return ResponseEntity.ok(ApiResponse.success(followService.getRequestedFollowerList(authUser.getId())));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("팔로우 신청 목록 조회 중 오류가 발생햇습니다."));
        }
    }

    // 팔로워 목록 조회
    @GetMapping("/follow/follower-list")
    public ResponseEntity<ApiResponse<?>> getFollowerList(@Auth AuthUser authUser) {
        try {
            return ResponseEntity.ok(ApiResponse.success(followService.getFollowerList(authUser.getId())));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("팔로워 신청 목록 조회 중 오류가 발생햇습니다."));
        }
    }

    // 팔로잉 목록 조회
    @GetMapping("/follow/following-list")
    public ResponseEntity<ApiResponse<?>> getFollowingList(@Auth AuthUser authUser) {
        try {
            return ResponseEntity.ok(ApiResponse.success(followService.getFollowingList(authUser.getId())));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("팔로잉 목록 조회 중 오류가 발생햇습니다."));
        }
    }

    // 팔로우 신청 수락
    @PutMapping("/follow/request/{followId}")
    public ResponseEntity<ApiResponse<?>> acceptFollowingRequest(@PathVariable long followId, @Auth AuthUser authUser) {
        try {
            return ResponseEntity.ok(ApiResponse.success(followService.acceptFollowingRequest(authUser.getId(), followId)));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("팔로우 신청 수락 중 오류가 발생햇습니다."));
        }
    }

    // 팔로우 신청 거절, 언팔로우, 팔로워 삭제
    @DeleteMapping("/follow/{followId}")
    public ResponseEntity<ApiResponse<?>> rejectFollowingRequest(@PathVariable long followId, @Auth AuthUser authUser) {
        try {
            followService.rejectFollowingRequest(authUser.getId(), followId);
            return ResponseEntity.ok(ApiResponse.success("삭제가 완료되었습니다."));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("삭제 오류가 발생햇습니다."));
        }
    }

}
