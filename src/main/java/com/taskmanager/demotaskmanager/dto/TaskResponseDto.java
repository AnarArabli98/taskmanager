package com.taskmanager.demotaskmanager.dto;

import com.taskmanager.demotaskmanager.model.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskResponseDto {

    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Date dueDate;
}
