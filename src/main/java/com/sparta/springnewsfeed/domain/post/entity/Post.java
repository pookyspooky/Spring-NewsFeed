package com.sparta.springnewsfeed.domain.post.entity;

import com.sparta.springnewsfeed.domain.file.entity.File;
import com.sparta.springnewsfeed.domain.likes.entity.Likes;
import com.sparta.springnewsfeed.global.entity.Timestamped;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "posts")
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> fileList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Likes> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public int getFileCount(){
        return fileList.size();
    }

    public int getLikeCount(){
        return likeList.size();
    }

    public int getCommentCount(){
        return commentList.size();
    }

    public void addFile(File file) {
        this.fileList.add(file);
        file.setPost(this);
    }

    public void removeFile(File file) {
        this.fileList.remove(file);
        file.setPost(null);
    }
}
