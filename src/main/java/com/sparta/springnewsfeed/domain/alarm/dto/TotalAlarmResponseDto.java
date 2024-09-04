package com.sparta.springnewsfeed.domain.alarm.dto;

import com.sparta.springnewsfeed.domain.alarm.entity.Alarm;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TotalAlarmResponseDto {
    private String alarmContent;
    private LocalDateTime createdAt;

    public TotalAlarmResponseDto(Alarm alarm) {
        this.alarmContent = alarm.getAlarmContent();
        this.createdAt = alarm.getCreatedAt();
    }
}
