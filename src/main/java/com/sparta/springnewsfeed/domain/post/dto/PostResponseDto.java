package com.sparta.springnewsfeed.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.springnewsfeed.domain.comment.dto.CommentResponseDto;
import com.sparta.springnewsfeed.domain.file.dto.FileDto;
import com.sparta.springnewsfeed.domain.likes.dto.LikesDto;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified_at;
    private List<FileDto> fileList;
    private List<LikesDto> likeList;
    private List<CommentResponseDto> commentList;

    public static PostResponseDto fromEntity(Post post){
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreated_at(post.getCreatedAt());
        dto.setModified_at(post.getModifiedAt());
        dto.setFileList(post.getFileList().stream()
                .map(FileDto::fromEntity)
                .collect(Collectors.toList()));
        dto.setLikeList(post.getLikeList().stream()
                .map(likes -> LikesDto.builder()
                        .username(likes.getUser().getUsername())
                        .build())
                .collect(Collectors.toList()));
        dto.setCommentList(post.getCommentList().stream()
                .map(CommentResponseDto::entityToDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
