package com.taskmanager.demotaskmanager.dto;

import com.taskmanager.demotaskmanager.model.Role;
import com.taskmanager.demotaskmanager.model.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private String fullName;
    private UserStatus status;
}
