package com.sparta.springnewsfeed.domain.profile.entity;

import com.sparta.springnewsfeed.domain.user.entity.Timestamped;
import com.sparta.springnewsfeed.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
