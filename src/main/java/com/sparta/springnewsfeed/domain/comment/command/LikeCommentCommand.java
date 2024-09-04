package com.sparta.springnewsfeed.domain.comment.command;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.likes.entity.CommentLikes;
import com.sparta.springnewsfeed.domain.likes.repository.CommentLikesRepository;
import com.sparta.springnewsfeed.domain.user.entity.User;

public class LikeCommentCommand implements Command {
    private CommentLikesRepository commentLikesRepository;
    private User user;
    private Comment comment;
    private CommentLikes commentLikes;

    public LikeCommentCommand(CommentLikesRepository commentLikesRepository, User user, Comment comment, CommentLikes commentLikes) {
        this.commentLikesRepository = commentLikesRepository;
        this.user = user;
        this.comment = comment;
        this.commentLikes = commentLikes;
    }

    @Override
    public void execute() {
        commentLikes = CommentLikes.builder()
                .user(user)
                .comment(comment)
                .isLike(true)
                .build();
        commentLikesRepository.save(commentLikes);
    }

    @Override
    public void undo() {
        if (commentLikes != null) {
            commentLikesRepository.delete(commentLikes);
        }
    }
}
