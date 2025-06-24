package com.taskmanager.demotaskmanager.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class LoginRequest {

    @NotBlank(message = "Email boş ola bilməz")
    @Email(message = "Email formati duzgun deyil")
    private String email;

    @NotBlank(message = "Password boş ola bilməz")
    private String password;
}
