package com.taskmanager.demotaskmanager.controller;

import com.taskmanager.demotaskmanager.dto.UserResponse;
import com.taskmanager.demotaskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "İstifadəçilər", description = "Yalnız admin istifadəçilər üçün")
public class UserController {


    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Bütün istifadəçiləri gətir", description = "Yalnız ADMIN rolu olan istifadəçilər istifadəçi siyahısını görə bilər")
    @ApiResponse(responseCode = "200", description = "İstifadəçi siyahısı qaytarıldı")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }
}
