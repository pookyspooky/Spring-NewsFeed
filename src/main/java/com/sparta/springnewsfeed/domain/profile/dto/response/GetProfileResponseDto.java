package com.sparta.springnewsfeed.domain.profile.dto.response;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.profile.entity.Profile;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetProfileResponseDto {
    private Long profileId;
    private String username;
    private String email;
    private String description;
    private List<String> posts;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likeCount;
    private String newAlarmCount;

    public GetProfileResponseDto(Profile profile, String newAlarmCount){
        this.profileId = profile.getId();
        this.username = profile.getUser().getUsername();
        this.email = profile.getUser().getEmail();
        this.description = profile.getDescription();
        this.posts = profile.getUser().getPostList().stream()
                .map(Post::getTitle)
                .collect(Collectors.toList());
        this.createdAt = profile.getCreatedAt();
        this.modifiedAt = profile.getModifiedAt();
        this.likeCount = profile.getLikeCount();
        this.newAlarmCount = newAlarmCount;
    }

}
