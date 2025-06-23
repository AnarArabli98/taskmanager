package com.taskmanager.demotaskmanager.repository;

import com.taskmanager.demotaskmanager.model.Task;
import com.taskmanager.demotaskmanager.model.User;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);
    List<Task> findAllByUser(User user);
}
