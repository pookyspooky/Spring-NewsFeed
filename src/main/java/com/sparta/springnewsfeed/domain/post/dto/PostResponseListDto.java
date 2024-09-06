package com.sparta.springnewsfeed.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class PostResponseListDto {
    private Long id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    private int fileCount;
    private int commentCount;
    private int likeCount;

    public static PostResponseListDto fromEntity(Post post){
        PostResponseListDto dto = new PostResponseListDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setModifiedAt(post.getModifiedAt());
        dto.setFileCount(post.getFileCount());
        dto.setCommentCount(post.getCommentCount());
        dto.setLikeCount(post.getLikeCount());
        return dto;
    }
}
