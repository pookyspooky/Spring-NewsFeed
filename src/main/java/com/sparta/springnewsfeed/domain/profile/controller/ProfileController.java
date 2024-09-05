package com.sparta.springnewsfeed.domain.profile.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.profile.dto.request.CreateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.request.UpdateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.CreateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.GetProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.UpdateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.service.ProfileService;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import com.sparta.springnewsfeed.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {

    private final ProfileService profileService;

    //프로필 등록
    @PostMapping("/profile")
    public ResponseEntity<ApiResponse<?>> createProfile(@RequestBody CreateProfileRequestDto createProfileRequestDto, @Auth AuthUser authUser){
        try {
            Long userId = authUser.getId();
            return ResponseEntity.ok(ApiResponse.success(profileService.createProfile(userId, createProfileRequestDto)));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("프로필 등록 중 오류가 발생햇습니다."));
        }
    }

    //프로필 조회
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<ApiResponse<?>> getProfile(@PathVariable(value = "profileId") Long profileId, @Auth AuthUser authUser){
        try {
            GetProfileResponseDto getProfileResponseDto = profileService.getProfileById(profileId, authUser.getId());
            return ResponseEntity.ok(ApiResponse.success(getProfileResponseDto));
        }  catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("프로필 조회 중 오류가 발생햇습니다."));
        }
    }

    //프로필 수정
    @PutMapping("/profile/{profileId}")
    public ResponseEntity<ApiResponse<?>> updateProfile(@PathVariable(value = "profileId") Long proileId, @RequestBody UpdateProfileRequestDto updateProfileRequestDto, @Auth AuthUser authUser){
        try {
            Long userId = authUser.getId();
            return ResponseEntity.ok(ApiResponse.success(profileService.updateProfile(proileId, updateProfileRequestDto, userId)));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("프로필 수정 중 오류가 발생햇습니다."));
        }
    }

    //좋아요 기능
    @PostMapping("/profile/{profileId}/likes")
    public void toggleLikeProfile(@PathVariable Long profileId, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        profileService.toggleLikeProfile(profileId, userId);
    }
}
