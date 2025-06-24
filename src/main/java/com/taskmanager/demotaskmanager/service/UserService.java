package com.taskmanager.demotaskmanager.service;

import com.taskmanager.demotaskmanager.dto.UserResponse;
import com.taskmanager.demotaskmanager.repository.UserRepository;
import com.taskmanager.demotaskmanager.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
}
