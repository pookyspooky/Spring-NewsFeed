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

    // 팔로워 요청자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    // 팔로잉 대상자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    // 승인 여부
    @Enumerated(EnumType.STRING)
    private CheckingAccepted accepted;


    public Follow(User followerId, User followingId, CheckingAccepted checkingAccepted) {
        this.follower = followerId;
        this.following = followingId;
        this.accepted = checkingAccepted;
    }

    // 팔로잉 요청
    public static Follow followingRequest(User followerId, User followingId, CheckingAccepted checkingAccepted) {
        return new Follow(followerId, followingId, checkingAccepted);
    }

    // 팔로우 수락
    public void update(CheckingAccepted checkingAccepted) {
        this.accepted = checkingAccepted;
    }

}
