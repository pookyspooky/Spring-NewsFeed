package com.sparta.springnewsfeed.domain.profile.service;

import com.sparta.springnewsfeed.domain.profile.dto.request.CreateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.CreateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.entity.Profile;
import com.sparta.springnewsfeed.domain.profile.repository.ProfileRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public CreateProfileResponseDto createProfile(Long id, CreateProfileRequestDto createProfileRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("선택한 유저가 존재하지 않습니다."));

        // 1. 프로필 생성
        Profile profile = new Profile(createProfileRequestDto, user);

        // 2. db 저장
        Profile saveProfile = profileRepository.save(profile);

        // 3. 저장된 프로필과 게시글을 응답 DTO 생성 후 반환
        return new CreateProfileResponseDto((saveProfile));
    }
}
