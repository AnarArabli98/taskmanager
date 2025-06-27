package com.taskmanager.demotaskmanager.controller;

import com.taskmanager.demotaskmanager.auth.AuthenticationResponse;
import com.taskmanager.demotaskmanager.auth.LoginRequest;
import com.taskmanager.demotaskmanager.auth.RegisterRequest;
import com.taskmanager.demotaskmanager.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "Authentication Controller", description = "Login və qeydiyyat əməliyyatları")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody RegisterRequest requestDto) {

        AuthenticationResponse response = authenticationService.register(requestDto);
        return ResponseEntity.ok(response);
    }


//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/api/admin/only")
//    public ResponseEntity<String> onlyAdminCanAccess() {
//        return ResponseEntity.ok("Bu səhifəni yalnız ADMIN görə bilər");
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @GetMapping("/api/user/profile")
//    public ResponseEntity<String> userProfile() {
//        return ResponseEntity.ok("Bu səhifəni USER və ADMIN görə bilər");
//    }

    @GetMapping("/me")
    public ResponseEntity<String> whoAmI(Principal principal) {
        return ResponseEntity.ok("Login olmuş istifadəçi: " + principal.getName());
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        AuthenticationResponse response = authenticationService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
