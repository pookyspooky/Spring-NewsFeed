package com.sparta.springnewsfeed.domain.profile.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.profile.dto.request.CreateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.request.UpdateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.CreateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.GetProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.UpdateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.service.ProfileService;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {

    private final ProfileService profileService;

    //프로필 등록
    @PostMapping("/profile")
    public ResponseEntity<CreateProfileResponseDto> createProfile(@RequestBody CreateProfileRequestDto createProfileRequestDto, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        return ResponseEntity.ok(profileService.createProfile(userId, createProfileRequestDto));
    }

    //프로필 조회
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<GetProfileResponseDto> getProfile(@PathVariable(value = "profileId") Long profileId){
        GetProfileResponseDto getProfileResponseDto = profileService.getProfileById(profileId);
        return ResponseEntity.ok(getProfileResponseDto);
    }

    //프로필 수정
    @PutMapping("/profile/{profileId}")
    public ResponseEntity<UpdateProfileResponseDto> updateProfile(@PathVariable(value = "profileId") Long proileId, @RequestBody UpdateProfileRequestDto updateProfileRequestDto, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        return ResponseEntity.ok(profileService.updateProfile(proileId, updateProfileRequestDto, userId));
    }

    //좋아요 기능
    @PostMapping("/profile/{profileId}/likes")
    public void toggleLikeProfile(@PathVariable Long profileId, @Auth AuthUser authUser){
        Long userId = authUser.getId();
        profileService.toggleLikeProfile(profileId, userId);
    }
}
