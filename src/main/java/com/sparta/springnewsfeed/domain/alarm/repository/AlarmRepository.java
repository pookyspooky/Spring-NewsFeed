package com.sparta.springnewsfeed.domain.alarm.repository;

import com.sparta.springnewsfeed.domain.alarm.entity.Alarm;
import com.sparta.springnewsfeed.domain.alarm.service.CheckingAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByAlarmReceiver_IdOrderByCreatedAtDesc(long userId);

    int countByAlarmReceiver_IdAndCheckingAlarm(long userId, CheckingAlarm checkingAlarm);
}
