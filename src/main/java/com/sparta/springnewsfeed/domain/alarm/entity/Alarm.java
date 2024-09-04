package com.sparta.springnewsfeed.domain.alarm.entity;

import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "alarm")
public class Alarm extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 알람 발신자 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarmSender_id")
    private User alarmSender;

    // 알람 수신자 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarmReceiver_id")
    private User alarmReceiver;

    // 알람 문구
    private String alarmContent;

    public Alarm(User alarmSenderId, User alarmReceiverId, String alarmContent) {
        this.alarmSender = alarmSenderId;
        this.alarmReceiver = alarmReceiverId;
        this.alarmContent = alarmContent;
    }

    public static Alarm followingRequestAlarm(User followerUser, User followigUser) {
        return new Alarm(followerUser, followigUser,followerUser.getUsername()+"님이 팔로우 신청을 보냈습니다.");
    }


    public static Alarm followingAcceptedAlarm(User followigUser, User followerUser) {
        return new Alarm(followigUser, followerUser,followigUser.getUsername()+"님이 팔로우 신청을 수락했습니다.");
    }
}
