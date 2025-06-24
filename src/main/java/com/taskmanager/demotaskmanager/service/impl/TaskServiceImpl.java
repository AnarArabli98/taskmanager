package com.taskmanager.demotaskmanager.service.impl;

import com.taskmanager.demotaskmanager.dto.TaskRequestDto;
import com.taskmanager.demotaskmanager.dto.TaskResponseDto;
import com.taskmanager.demotaskmanager.model.Task;
import com.taskmanager.demotaskmanager.model.TaskStatus;
import com.taskmanager.demotaskmanager.model.User;
import com.taskmanager.demotaskmanager.repository.TaskRepository;
import com.taskmanager.demotaskmanager.repository.UserRepository;
import com.taskmanager.demotaskmanager.service.TaskService;
import com.taskmanager.demotaskmanager.utils.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
    public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;


    //  Entity → DTO

    private TaskResponseDto mapToDto(Task task) {
        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Istifadeci tapilmadi"));

        TaskStatus taskStatus = requestDto.getStatus() != null
                ? requestDto.getStatus()
                : TaskStatus.TODO;

        Task task = taskMapper.toEntity(requestDto,user);
        return taskMapper.toDto(taskRepository.save(task));

    }

    //  Task-ı redaktə et

    @Override
    public TaskResponseDto updateTask(Long id ,TaskRequestDto requestDto, String email) {
        Task task = getUserTask(id,email);

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
        Task task = getUserTask(id,email);
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



    // Admin üçün bütün task-lar
    @Override
    public List<TaskResponseDto> getAllTasksForAdmin() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::mapToDto)
                .toList();
    }


    // Task axtarışı (title əsasında)
    @Override
    public List<TaskResponseDto> searchByTitle(String title) {
        return taskRepository.findByTitleContainsIgnoreCase(title)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    // Müəyyən tarixdən əvvəlki due date-li task-lar
    @Override
    public List<TaskResponseDto> findDueTasksBefore(Date date) {
        return taskRepository.findByDuedateBefore(date)
                .stream()
                .map(this::mapToDto)
                .toList();
    }
}
