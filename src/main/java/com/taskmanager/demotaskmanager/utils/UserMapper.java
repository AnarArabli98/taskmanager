package com.taskmanager.demotaskmanager.utils;

import com.taskmanager.demotaskmanager.dto.UserResponse;
import com.taskmanager.demotaskmanager.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        if(user == null) {
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .email(user.getEmail())
                .status(user.getStatus())
                .build();
    }
}
