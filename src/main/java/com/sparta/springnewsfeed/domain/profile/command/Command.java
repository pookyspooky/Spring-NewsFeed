package com.sparta.springnewsfeed.domain.profile.command;

public interface Command {
    void execute();
    void undo();
}
