package com.sparta.springnewsfeed.domain.follow.service;

import com.sparta.springnewsfeed.domain.follow.dto.response.FollowingResponseDto;
import com.sparta.springnewsfeed.domain.follow.entity.Follow;
import com.sparta.springnewsfeed.domain.follow.repository.FollowRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    // 팔로우 신청
    @Transactional
    public FollowingResponseDto followingRequest(long userId, long followingId) {
        // 팔로우 신청자 존재 확인
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 없습니다."));
        // 팔로우 대상자 존재 확인
        User followigUser = userRepository.findById(followingId).orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 없습니다."));

        // 중복 팔로우 요청 확인
        Optional<Follow> checkingRequest = followRepository.findByUser_IdAndFollowing_Id(userId, followingId);

        if (checkingRequest.isPresent()) {
            throw new IllegalArgumentException("이미 처리된 요청입니다.");
        } else {
            // DB 저장
            Follow follow = Follow.followingRequest(user, followigUser, String.valueOf(CheckingAccepted.NOT_YET));
            followRepository.save(follow);
            // Entity -> response
            return new FollowingResponseDto(follow);
        }

    }

    // 팔로우 신청 목록 조회
//    @Transactional
//    public FollowingResponseDto getRequestedFollowerList(long userId) {
//        // userId 를 팔로잉하는 유저듣 중 수락 대기중인 유저들 리스트
//        List<FollowList> requestFollowerList = followListRepository.findAllByFollowing_IdAndAccepted(userId, CheckingAccepted.NOT_YET);
//
//        for (FollowList requestFollower : requestFollowerList) {
//
//        }
//
//    }


    // 팔로우 신청 승인
    @Transactional
    public FollowingResponseDto acceptFollowingRequest(long followId) {
        // 중복 팔로우 요청 확인
        Follow checkingRequest = followRepository.findById(followId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 요청입니다."));

        if (checkingRequest.getAccepted().equals("ACCEPTED")) {
            throw new IllegalArgumentException("이미 처리된 요청입니다.");
        }
        // DB 저장
        checkingRequest.update(String.valueOf(CheckingAccepted.ACCEPTED));
        // Entity -> response
        return new FollowingResponseDto(checkingRequest);
    }

    // 팔로우 신청 거절, 언팔로우, 팔로워 삭제
    @Transactional
    public void rejectFollowingRequest(long followId) {
        // 중복 팔로우 요청 확인
        Follow checkingRequest = followRepository.findById(followId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 요청입니다."));

        // 팔로우 신청 거절, 언팔로우, 팔로워 삭제
        followRepository.delete(checkingRequest);
    }


}
