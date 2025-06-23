package com.taskmanager.demotaskmanager.dto;

import com.taskmanager.demotaskmanager.model.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequestDto {

    @NotBlank(message = "Title boş ola bilməz")
    @Size(min = 3, max = 100)
    private String title;

    @Size(max = 200 , message = "Description 200 simvoldan cox ola bilmez")
    private String description;
    private TaskStatus status;

}
