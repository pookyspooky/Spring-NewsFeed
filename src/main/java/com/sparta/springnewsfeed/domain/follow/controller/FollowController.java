package com.sparta.springnewsfeed.domain.follow.controller;

import com.sparta.springnewsfeed.domain.follow.dto.response.FollowerResponseDto;
import com.sparta.springnewsfeed.domain.follow.dto.response.FollowingResponseDto;
import com.sparta.springnewsfeed.domain.follow.service.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<FollowingResponseDto> followingRequest(@PathVariable long followingId, HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        return ResponseEntity.ok(followService.followingRequest(userId, followingId));
    }

    // 팔로우 신청 목록 조회
    @GetMapping("/follow/request/followerList")
    public ResponseEntity<List<FollowerResponseDto>> getRequestedFollowerList(HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        return ResponseEntity.ok(followService.getRequestedFollowerList(userId));
    }

    // 팔로워 목록 조회
    @GetMapping("/follow/followerList")
    public ResponseEntity<List<FollowerResponseDto>> getFollowerList(HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        return ResponseEntity.ok(followService.getFollowerList(userId));
    }

    // 팔로잉 목록 조회
    @GetMapping("/follow/followingList")
    public ResponseEntity<List<FollowingResponseDto>> getFollowingList(HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        return ResponseEntity.ok(followService.getFollowingList(userId));
    }

    // 팔로우 신청 수락
    @PutMapping("/follow/request/{followId}")
    public ResponseEntity<FollowingResponseDto> acceptFollowingRequest(@PathVariable long followId, HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        return ResponseEntity.ok(followService.acceptFollowingRequest(userId, followId));
    }

    // 팔로우 신청 거절, 언팔로우, 팔로워 삭제
    @DeleteMapping("/follow/{followId}")
    public ResponseEntity<String> rejectFollowingRequest(@PathVariable long followId, HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        followService.rejectFollowingRequest(userId, followId);
        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }

}
