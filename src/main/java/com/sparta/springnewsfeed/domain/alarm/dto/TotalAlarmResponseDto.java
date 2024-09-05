package com.sparta.springnewsfeed.domain.alarm.dto;

import com.sparta.springnewsfeed.domain.alarm.entity.Alarm;
import com.sparta.springnewsfeed.domain.alarm.service.CheckingAlarm;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TotalAlarmResponseDto {
    private String alarmContent;
    private LocalDateTime createdAt;
    private CheckingAlarm checkingAlarm;

    public TotalAlarmResponseDto(Alarm alarm) {
        this.alarmContent = alarm.getAlarmContent();
        this.createdAt = alarm.getCreatedAt();
        this.checkingAlarm = alarm.getCheckingAlarm();
    }
}
