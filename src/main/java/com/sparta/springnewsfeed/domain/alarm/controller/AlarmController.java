package com.sparta.springnewsfeed.domain.alarm.controller;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.alarm.dto.FollowAlarmResponseDto;
import com.sparta.springnewsfeed.domain.alarm.dto.TotalAlarmResponseDto;
import com.sparta.springnewsfeed.domain.alarm.service.AlarmService;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import com.sparta.springnewsfeed.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AlarmController {


    private final AlarmService alarmService;

    // 알람 목록 조회
    @GetMapping("/alarm-list")
    public ResponseEntity<ApiResponse<?>> getAlarmList(@Auth AuthUser authUser) {
        try {
            return ResponseEntity.ok(ApiResponse.success(alarmService.getAlarmList(authUser.getId())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("알람 목록 조회 중 오류가 발생햇습니다."));
        }
    }

}
