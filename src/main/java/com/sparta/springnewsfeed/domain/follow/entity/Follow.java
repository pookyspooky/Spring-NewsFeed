package com.sparta.springnewsfeed.domain.follow.entity;

import com.sparta.springnewsfeed.domain.follow.service.CheckingAccepted;
import com.sparta.springnewsfeed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 본인
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 팔로잉 대상자
    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    // 승인 여부
    @Enumerated(EnumType.STRING)
    private CheckingAccepted accepted;


    public Follow(User userId, User followingId, CheckingAccepted checkingAccepted) {
        this.user = userId;
        this.following = followingId;
        this.accepted = checkingAccepted;
    }

    // 팔로잉 요청
    public static Follow followingRequest(User userId, User followingId, CheckingAccepted checkingAccepted) {
        return new Follow(userId, followingId, checkingAccepted);
    }

    public void update(CheckingAccepted checkingAccepted) {
        this.accepted = checkingAccepted;
    }

}
