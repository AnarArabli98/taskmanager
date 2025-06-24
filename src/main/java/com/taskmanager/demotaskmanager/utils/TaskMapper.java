package com.taskmanager.demotaskmanager.utils;

import com.taskmanager.demotaskmanager.dto.TaskRequestDto;
import com.taskmanager.demotaskmanager.dto.TaskResponseDto;
import com.taskmanager.demotaskmanager.model.Task;
import com.taskmanager.demotaskmanager.model.TaskStatus;
import com.taskmanager.demotaskmanager.model.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponseDto toDto(Task task) {
        if (task == null) {
            return null;
        }

        return TaskResponseDto.builder()
                .id(task.getId())
                .status(task.getStatus())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDuedate())
                .build();

    }
    public Task toEntity(TaskRequestDto taskRequestDto, User user) {
        if (taskRequestDto == null || user == null) {
            return null;
        }

        return Task.builder()
                .title(taskRequestDto.getTitle())
                .description(taskRequestDto.getDescription())
                .status(taskRequestDto.getStatus() != null ? taskRequestDto.getStatus() : TaskStatus.TODO)
                .duedate(taskRequestDto.getDueDate())
                .user(user)
                .build();
    }
}
