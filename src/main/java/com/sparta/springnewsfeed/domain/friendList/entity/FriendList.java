package com.sparta.springnewsfeed.domain.friendList.entity;

import com.sparta.springnewsfeed.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table()
public class FriendList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private User following;

    @Column
    private String accepted;
}
