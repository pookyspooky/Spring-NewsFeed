package com.sparta.springnewsfeed.domain.alarm.service;

import com.sparta.springnewsfeed.domain.alarm.dto.TotalAlarmResponseDto;
import com.sparta.springnewsfeed.domain.alarm.entity.Alarm;
import com.sparta.springnewsfeed.domain.alarm.repository.AlarmRepository;
import com.sparta.springnewsfeed.domain.follow.dto.response.FollowerResponseDto;
import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    // 알람 목록 조회
    public List<TotalAlarmResponseDto> getAlarmList(long userId) {
        List<Alarm> alarmList = alarmRepository.findAllByAlarmReceiver_IdOrderByCreatedAtDesc(userId);

        return alarmList.stream().map(TotalAlarmResponseDto::new).toList();
    }
}
