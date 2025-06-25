package com.taskmanager.demotaskmanager.exception;

import org.aspectj.bridge.IMessage;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
