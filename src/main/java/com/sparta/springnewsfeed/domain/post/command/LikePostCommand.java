package com.sparta.springnewsfeed.domain.post.command;

import com.sparta.springnewsfeed.domain.likes.entity.Likes;
import com.sparta.springnewsfeed.domain.likes.repository.LikesRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.user.entity.User;

public class LikePostCommand implements Command{
    private LikesRepository likesRepository;
    private User user;
    private Post post;
    private Likes likes;

    public LikePostCommand(LikesRepository likesRepository, User user, Post post, Likes likes) {
        this.likesRepository = likesRepository;
        this.user = user;
        this.post = post;
        this.likes = likes;
    }

    @Override
    public void execute() {
        likes = Likes.builder()
                .user(user)
                .post(post)
                .isLike(true)
                .build();
        likesRepository.save(likes);
    }

    @Override
    public void undo() {
        if (likes != null){
            likesRepository.delete(likes);
        }
    }
}
