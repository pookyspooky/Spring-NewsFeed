package com.sparta.springnewsfeed.domain.alarm.repository;

import com.sparta.springnewsfeed.domain.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByAlarmReceiver_IdOrderByCreatedAtDesc(long userId);

}
