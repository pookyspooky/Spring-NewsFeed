package com.sparta.springnewsfeed.domain.post.command;

public interface Command {
    void execute();
    void undo();
}
