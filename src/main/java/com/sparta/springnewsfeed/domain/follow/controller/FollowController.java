package com.sparta.springnewsfeed.domain.follow.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.follow.dto.response.FollowerResponseDto;
import com.sparta.springnewsfeed.domain.follow.dto.response.FollowingResponseDto;
import com.sparta.springnewsfeed.domain.follow.service.FollowService;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<FollowingResponseDto> followingRequest(@PathVariable long followingId, @Auth AuthUser authUser) {
        return ResponseEntity.ok(followService.followingRequest(authUser.getId(), followingId));
    }

    // 팔로우 신청 목록 조회
    @GetMapping("/follow/request/follower-list")
    public ResponseEntity<List<FollowerResponseDto>> getRequestedFollowerList(@Auth AuthUser authUser) {
        return ResponseEntity.ok(followService.getRequestedFollowerList(authUser.getId()));
    }

    // 팔로워 목록 조회
    @GetMapping("/follow/follower-list")
    public ResponseEntity<List<FollowerResponseDto>> getFollowerList(@Auth AuthUser authUser) {
        return ResponseEntity.ok(followService.getFollowerList(authUser.getId()));
    }

    // 팔로잉 목록 조회
    @GetMapping("/follow/following-list")
    public ResponseEntity<List<FollowingResponseDto>> getFollowingList(@Auth AuthUser authUser) {
        return ResponseEntity.ok(followService.getFollowingList(authUser.getId()));
    }

    // 팔로우 신청 수락
    @PutMapping("/follow/request/{followId}")
    public ResponseEntity<FollowerResponseDto> acceptFollowingRequest(@PathVariable long followId, @Auth AuthUser authUser) {
        return ResponseEntity.ok(followService.acceptFollowingRequest(authUser.getId(), followId));
    }

    // 팔로우 신청 거절, 언팔로우, 팔로워 삭제
    @DeleteMapping("/follow/{followId}")
    public ResponseEntity<String> rejectFollowingRequest(@PathVariable long followId, @Auth AuthUser authUser) {
        followService.rejectFollowingRequest(authUser.getId(), followId);
        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }

}
