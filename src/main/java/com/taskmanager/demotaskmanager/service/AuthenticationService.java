package com.taskmanager.demotaskmanager.service;

import com.taskmanager.demotaskmanager.auth.AuthenticationResponse;
import com.taskmanager.demotaskmanager.auth.LoginRequest;
import com.taskmanager.demotaskmanager.auth.RegisterRequest;
import com.taskmanager.demotaskmanager.security.JwtService;
import com.taskmanager.demotaskmanager.model.Role;
import com.taskmanager.demotaskmanager.model.User;
import com.taskmanager.demotaskmanager.model.UserStatus;
import com.taskmanager.demotaskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getName())) {
            throw new RuntimeException("Username artıq mövcuddur");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email artiq istifade olunur");
        }

        Role role = userRepository.count() == 0 ? Role.ADMIN : Role.USER;

        User user = User.builder()
                .username(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .status(UserStatus.PENDING)
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);

        return new AuthenticationResponse(token,
                savedUser.getUsername(),
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole());

    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword())
        );
        //Əgər şifrə və istifadəçi uyğun deyilsə, Spring avtomatik BadCredentialsException atır
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("İstifadəçi tapılmadı"));

        String token = jwtService.generateToken(user);
        return  new AuthenticationResponse(token,
                user.getUsername(),
                user.getId(),
                user.getEmail(),
                user.getRole());
    }


}
