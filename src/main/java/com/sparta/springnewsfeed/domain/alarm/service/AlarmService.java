package com.sparta.springnewsfeed.domain.alarm.service;

import com.sparta.springnewsfeed.domain.alarm.dto.TotalAlarmResponseDto;
import com.sparta.springnewsfeed.domain.alarm.entity.Alarm;
import com.sparta.springnewsfeed.domain.alarm.repository.AlarmRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    // 알람 목록 조회
    @Transactional
    public List<TotalAlarmResponseDto> getAlarmList(long userId) {
        List<Alarm> alarmList = alarmRepository.findAllByAlarmReceiver_IdOrderByCreatedAtDesc(userId);
        List<TotalAlarmResponseDto> responseList = new ArrayList<>();

        for (Alarm alarm : alarmList) {
            alarm.update(CheckingAlarm.CHECKED);
            responseList.add(new TotalAlarmResponseDto(alarm));
        }
        return responseList;
    }
}
