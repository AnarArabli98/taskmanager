package com.taskmanager.demotaskmanager.service;

import com.taskmanager.demotaskmanager.dto.TaskRequestDto;
import com.taskmanager.demotaskmanager.dto.TaskResponseDto;
import com.taskmanager.demotaskmanager.model.Task;

import java.util.List;

public interface TaskService {


    void deleteTask(Long id,String email);
    List<TaskResponseDto> getUserTasks(String email);
    TaskResponseDto updateTask(Long id,TaskRequestDto taskRequestDto,String email);
    TaskResponseDto createTask(TaskRequestDto requestDto, String email);
}
