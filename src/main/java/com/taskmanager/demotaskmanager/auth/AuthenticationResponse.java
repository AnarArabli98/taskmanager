package com.taskmanager.demotaskmanager.auth;

import com.taskmanager.demotaskmanager.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String username;
    private Long userId;
    private String email;
    private Role role;

}
