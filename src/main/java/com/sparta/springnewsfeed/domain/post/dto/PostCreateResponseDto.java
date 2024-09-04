package com.sparta.springnewsfeed.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PostCreateResponseDto {
    private Long id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified_at;

    public static PostCreateResponseDto fromEntity(Post post){
        PostCreateResponseDto dto = new PostCreateResponseDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreated_at(post.getCreatedAt());
        dto.setModified_at(post.getModifiedAt());
        return dto;
    }
}
