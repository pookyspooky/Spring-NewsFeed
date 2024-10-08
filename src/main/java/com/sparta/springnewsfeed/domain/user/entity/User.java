package com.sparta.springnewsfeed.domain.user.entity;

import com.sparta.springnewsfeed.domain.alarm.entity.Alarm;
import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.follow.entity.Follow;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.profile.entity.Profile;
import com.sparta.springnewsfeed.domain.user.dto.UserRequestDto;
import com.sparta.springnewsfeed.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name ="users")
@NoArgsConstructor
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;
    private String username;
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.REMOVE)
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.REMOVE)
    private List<Follow> followings = new ArrayList<>();

    @OneToMany(mappedBy = "alarmSender", cascade = CascadeType.REMOVE)
    private List<Alarm> alarmSenders = new ArrayList<>();

    @OneToMany(mappedBy = "alarmReceiver", cascade = CascadeType.REMOVE)
    private List<Alarm> alarmReceivers = new ArrayList<>();

    public User(UserRequestDto userRequest, String password){
        this.username = userRequest.getUsername();
        this.email = userRequest.getEmail();
        this.password = password;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
