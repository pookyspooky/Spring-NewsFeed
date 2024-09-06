package com.sparta.springnewsfeed.domain.post.dto;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private MultipartFile image;

    public Post toEntity(){
        Post post = new Post();
        post.setTitle(this.title);
        post.setContent(this.content);
        return post;
    }

    public boolean hasTitle(){
        return title != null;
    }
    public boolean hasContent(){
        return content != null;
    }
}
