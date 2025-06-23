package com.taskmanager.demotaskmanager.service.impl;

import com.taskmanager.demotaskmanager.dto.TaskRequestDto;
import com.taskmanager.demotaskmanager.dto.TaskResponseDto;
import com.taskmanager.demotaskmanager.model.Task;
import com.taskmanager.demotaskmanager.model.TaskStatus;
import com.taskmanager.demotaskmanager.model.User;
import com.taskmanager.demotaskmanager.repository.TaskRepository;
import com.taskmanager.demotaskmanager.repository.UserRepository;
import com.taskmanager.demotaskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
    public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;



    //  Entity → DTO

    private TaskResponseDto mapToDto(Task task) {
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .dueDate(task.getDuedate())
                .build();
    }

    //  Task-ı redaktə et

    @Override
    public TaskResponseDto updateTask(Long id ,TaskRequestDto requestDto, String email) {
        Task task = (Task) getUserTasks(email);

        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setStatus(requestDto.getStatus() != null
                ? requestDto.getStatus()
                : TaskStatus.TODO);
        return mapToDto(taskRepository.save(task));

    }

    // task silmek
    @Override
    public void deleteTask(Long id , String email) {
        Task task = (Task) getUserTasks(email);
        taskRepository.delete(task);

    }

    //  Başqasının task-ına çıxışa icazə yoxdur
    private Task getUserTask(Long id, String email) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task tapilmadi"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new AccessDeniedException("Bu task size aid deil");
        }
        return task;
    }

    // tasklari siyahilama

    @Override
    public List<TaskResponseDto> getUserTasks(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Istifadeci tapilmadi"));

        return taskRepository.findAllByUser(user)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Istifadeci tapilmadi"));

        TaskStatus taskStatus = requestDto.getStatus() != null
                ? requestDto.getStatus()
                : TaskStatus.TODO;

        Task task = Task.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .status(taskStatus)
                .user(user)
                .build();

        return mapToDto(taskRepository.save(task));
    }
}
