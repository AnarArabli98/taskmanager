package com.taskmanager.demotaskmanager.auth;

import com.taskmanager.demotaskmanager.dto.LoginRequest;
import com.taskmanager.demotaskmanager.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        AuthenticationResponse response = authenticationService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
