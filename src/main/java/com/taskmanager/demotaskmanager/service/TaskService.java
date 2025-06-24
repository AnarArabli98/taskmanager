package com.taskmanager.demotaskmanager.service;

import com.taskmanager.demotaskmanager.dto.TaskRequestDto;
import com.taskmanager.demotaskmanager.dto.TaskResponseDto;

import java.util.Date;
import java.util.List;

public interface TaskService {


    void deleteTask(Long id,String email);
    List<TaskResponseDto> getUserTasks(String email);
    TaskResponseDto updateTask(Long id,TaskRequestDto taskRequestDto,String email);
    TaskResponseDto createTask(TaskRequestDto requestDto, String email);
    List<TaskResponseDto> getAllTasksForAdmin(Long id);
    List<TaskResponseDto> searchByTitle(String title);
    List<TaskResponseDto> findDueTasksBefore(Date date);
}
