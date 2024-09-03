package com.sparta.springnewsfeed.domain.profile.controller;

import com.sparta.springnewsfeed.domain.profile.dto.request.CreateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.CreateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/profile/{id}")
    public ResponseEntity<CreateProfileResponseDto> createProfile(@PathVariable(value = "id") Long id, @RequestBody CreateProfileRequestDto createProfileRequestDto){
        return ResponseEntity.ok(profileService.createProfile(id, createProfileRequestDto));
    }

//    @GetMapping("/profile/{userId}")
//    public ResponseEntity<GetProfileResponseDto> getProfile(@PathVariable(value = "userId") Long userId){
//        return ResponseEntity.ok(profileService.getUserById(userId));
//    }
}
