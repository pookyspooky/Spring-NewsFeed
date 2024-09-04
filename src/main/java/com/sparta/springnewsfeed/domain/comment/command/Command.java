package com.sparta.springnewsfeed.domain.comment.command;

public interface Command {
    void execute();
    void undo();
}
