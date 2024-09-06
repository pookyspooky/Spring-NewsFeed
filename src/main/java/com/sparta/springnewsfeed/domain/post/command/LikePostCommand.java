package com.sparta.springnewsfeed.domain.post.command;

import com.sparta.springnewsfeed.domain.likes.entity.PostLikes;
import com.sparta.springnewsfeed.domain.likes.repository.LikesRepository;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.user.entity.User;

public class LikePostCommand implements Command{
    private LikesRepository likesRepository;
    private User user;
    private Post post;
    private PostLikes postLikes;

    public LikePostCommand(LikesRepository likesRepository, User user, Post post, PostLikes postLikes) {
        this.likesRepository = likesRepository;
        this.user = user;
        this.post = post;
        this.postLikes = postLikes;
    }

    @Override
    public void execute() {
        postLikes = PostLikes.builder()
                .user(user)
                .post(post)
                .isLike(true)
                .build();
        likesRepository.save(postLikes);
    }

    @Override
    public void undo() {
        if (postLikes != null){
            likesRepository.delete(postLikes);
        }
    }
}
