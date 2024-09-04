package com.sparta.springnewsfeed.domain.profile.service;

import com.sparta.springnewsfeed.domain.profile.dto.request.CreateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.request.UpdateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.CreateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.GetProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.UpdateProfileResponseDto;
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
        // 1. 사용자 조회
        User user = findeUserById(id);

        // 2. 이미 프로필이 있는지 확인
        if(user.getProfile() != null){
            throw new IllegalArgumentException("이미 프로필이 등록된 사용자입니다.");
        }

        // 3. 프로필 생성
        Profile profile = new Profile(createProfileRequestDto, user);

        // 4. DB 저장
        Profile saveProfile = profileRepository.save(profile);

        // 5. 저장된 프로필과 게시글을 응답 DTO 생성 후 반환
        return new CreateProfileResponseDto((saveProfile));
    }

    private User findeUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("선택한 유저가 존재하지 않습니다."));
    }

    public UpdateProfileResponseDto updateProfile(Long id, UpdateProfileRequestDto updateProfileRequestDto) {
       try{
           // 1. 프로필 확인
           Profile profile = profileRepository.findById(id)
                   .orElseThrow(()-> new IllegalArgumentException("선택한 프로필이 없습니다."));

           // 2. 프로필 수정
           profile.updateProfile(updateProfileRequestDto);

           // 3. 프로필 저장
           profileRepository.save(profile);

           // 4. 응답 반환
           return new UpdateProfileResponseDto(profile.getId(), profile.getDescription());
       }catch (Exception e){
           throw new IllegalArgumentException("프로필 업데이트 중 문제가 발생했습니다.", e);
       }

    }

    public GetProfileResponseDto getProfileById(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()-> new IllegalArgumentException("선택한 프로필이 없습니다."));

        return new GetProfileResponseDto(profile);
    }
}
