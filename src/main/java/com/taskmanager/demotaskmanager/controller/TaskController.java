package com.taskmanager.demotaskmanager.controller;



import com.taskmanager.demotaskmanager.dto.TaskRequestDto;
import com.taskmanager.demotaskmanager.dto.TaskResponseDto;
import com.taskmanager.demotaskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody @Valid TaskRequestDto requestDto,
                                                      Principal principal) {

        TaskResponseDto responseDto = taskService.createTask(requestDto, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //İstifadəçinin öz tasklarını əldə et
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAll(Principal principal) {

        List<TaskResponseDto> tasks = taskService.getUserTasks(principal.getName());
        return ResponseEntity.ok(tasks);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksForAdmin() {
        return ResponseEntity.ok(taskService.getAllTasksForAdmin());
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id
            ,@RequestBody @Valid TaskRequestDto requestDto
            , Principal principal) {

        TaskResponseDto updateTaskresponseDto = taskService.updateTask(id, requestDto, principal.getName());
        return ResponseEntity.ok(updateTaskresponseDto);

    }

    @GetMapping("/due")
    public ResponseEntity<List<TaskResponseDto>> getDueTasks(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok(taskService.findDueTasksBefore(date));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id, Principal principal) {
        taskService.deleteTask(id, principal.getName());
        return ResponseEntity.noContent().build(); // 204 No Content qaytarmaq ucun HTTP status kodu
    }
}
