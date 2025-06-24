package com.taskmanager.demotaskmanager.dto;

import com.taskmanager.demotaskmanager.model.TaskStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRequestDto {

    @NotBlank(message = "Başlıq boş ola bilməz")
    @Size(min = 3, max = 100, message = "Başlıq 3-100 simvol arasında olmalıdır")
    private String title;

    @Size(max = 200, message = "Təsvir 200 simvoldan artıq ola bilməz")
    private String description;

    private TaskStatus status;

    @Future(message = "Bitmə tarixi keçmiş ola bilməz")
    private Date dueDate;

}
