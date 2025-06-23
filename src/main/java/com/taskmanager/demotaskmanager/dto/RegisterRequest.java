package com.taskmanager.demotaskmanager.dto;

import com.taskmanager.demotaskmanager.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Username boş ola bilməz")
    @Size(min = 4, max = 20, message = "Username 4 ilə 20 simvol arasında olmalıdır")
    private String name;

    @NotBlank(message = "Password boş ola bilməz")
    @Size(min = 6, message = "Password minimum 6 simvol olmalıdır")
    private String password;

    @NotBlank(message = "Email boş ola bilməz")
    @Email(message = "Email formatı düzgün deyil")
    private String email;

    @NotBlank(message = "Ad ve Soyad daxil et")
    private String fullName;
}
