package com.taskmanager.demotaskmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email boş ola bilməz")
    private String email;
    @NotBlank(message = "Password boş ola bilməz")
    private String password;
}
