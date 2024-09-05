package com.sparta.springnewsfeed.domain.profile.service;

import com.sparta.springnewsfeed.domain.alarm.entity.Alarm;
import com.sparta.springnewsfeed.domain.alarm.repository.AlarmRepository;
import com.sparta.springnewsfeed.domain.alarm.service.CheckingAlarm;
import com.sparta.springnewsfeed.domain.likes.entity.ProfileLikes;
import com.sparta.springnewsfeed.domain.likes.repository.ProfileLikesRepository;
import com.sparta.springnewsfeed.domain.profile.command.Command;
import com.sparta.springnewsfeed.domain.profile.command.LikeProfileCommand;
import com.sparta.springnewsfeed.domain.profile.dto.request.CreateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.request.UpdateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.CreateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.GetProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.dto.response.UpdateProfileResponseDto;
import com.sparta.springnewsfeed.domain.profile.entity.Profile;
import com.sparta.springnewsfeed.domain.profile.repository.ProfileRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import com.sparta.springnewsfeed.global.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

    private static final String USER_ERROR_MESSAGE = "유저를 찾을 수 없습니다.";
    private static final String PROFILE_ERROR_MESSAGE = "프로필을 찾을 수 없습니다.";
    private static final String UNAUTHORIZED_ERROR_MESSAGE = "권한이 없습니다.";
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileLikesRepository profileLikesRepository;
    private final AlarmRepository alarmRepository;

    // 프로필 등록
    public CreateProfileResponseDto createProfile(Long userId, CreateProfileRequestDto createProfileRequestDto) {
        // 1. 사용자 조회
        User user = findeUserById(userId);

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

    // 유저 확인
    private User findeUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("선택한 유저가 존재하지 않습니다."));
    }

    // 프로필 수정
    public UpdateProfileResponseDto updateProfile(Long id, UpdateProfileRequestDto updateProfileRequestDto, Long userId) {
        try{
            // 1. 프로필 확인
            Profile profile = profileRepository.findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("선택한 프로필이 없습니다."));

            if(!profile.getUser().getId().equals(userId))
                throw new UnauthorizedAccessException(UNAUTHORIZED_ERROR_MESSAGE);

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

    // 프로필 조회
    @Transactional(readOnly = true)
    public GetProfileResponseDto getProfileById(Long profileId, long userId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()-> new IllegalArgumentException("선택한 프로필이 없습니다."));

        String newAlarmCount;
        if (userId == profileId) {
            newAlarmCount = String.valueOf(alarmRepository.countByAlarmReceiver_IdAndCheckingAlarm(userId, CheckingAlarm.NEW));
        } else {
            newAlarmCount = "null";
        }
            return new GetProfileResponseDto(profile, newAlarmCount);

    }

    // 프로필 좋아요
    public void toggleLikeProfile(Long profileId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(USER_ERROR_MESSAGE));

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException(PROFILE_ERROR_MESSAGE));

        ProfileLikes existingLike = profileLikesRepository.findByUserAndProfile(user, profile);

        if(profile.getUser().getId().equals(userId))
            throw new UnauthorizedAccessException(UNAUTHORIZED_ERROR_MESSAGE);

        if(existingLike != null){
            Command unlikeCommand = new LikeProfileCommand(profileLikesRepository, user, profile, existingLike);
            unlikeCommand.undo();

            // 알림 저장
            // 로그인 유저와 프로필 유저가 일치하면 알림 저장 X
            if (!user.getId().equals(profile.getUser().getId())) {
                Alarm alarm = Alarm.LikeProfileAlarm(user, profile.getUser());
                alarmRepository.save(alarm);
            }
        }else {
            Command likeCommand = new LikeProfileCommand(profileLikesRepository, user, profile, null);
            likeCommand.execute();
        }
    }
}
