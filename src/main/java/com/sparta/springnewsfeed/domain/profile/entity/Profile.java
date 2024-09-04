package com.sparta.springnewsfeed.domain.profile.entity;

import com.sparta.springnewsfeed.domain.likes.entity.ProfileLikes;
import com.sparta.springnewsfeed.domain.profile.dto.request.CreateProfileRequestDto;
import com.sparta.springnewsfeed.domain.profile.dto.request.UpdateProfileRequestDto;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "profiles")
@NoArgsConstructor
public class Profile extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @Column
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<ProfileLikes> likeList = new ArrayList<>();

    public Profile(CreateProfileRequestDto createProfileRequestDto, User user) {
        this.description = createProfileRequestDto.getDescription();
        this.user = user;
    }

    public void updateProfile(UpdateProfileRequestDto updateProfileRequestDto) {
        this.description = updateProfileRequestDto.getDescription();
    }

    public int getLikeCount(){
        return likeList.size();
    }
}
