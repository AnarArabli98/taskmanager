package com.taskmanager.demotaskmanager.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "İstifadəçi adı boş ola bilməz")
    @Size(min = 4, max = 20, message = "İstifadəçi adı 4-20 simvol arasında olmalıdır")
    private String name;

    @NotBlank(message = "Şifrə boş ola bilməz")
    @Size(min = 6, message = "Şifrə minimum 6 simvol olmalıdır")
    private String password;

    @NotBlank(message = "Email boş ola bilməz")
    @Email(message = "Email formatı düzgün deyil")
    private String email;

    @NotBlank(message = "Ad və soyad boş ola bilməz")
    @Size(min = 3, max = 50)
    private String fullName;
}
