package com.taskmanager.demotaskmanager.repository;

import com.taskmanager.demotaskmanager.model.Task;
import com.taskmanager.demotaskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(User user);
    List<Task> findByTitleContainsIgnoreCase(String title);
    List<Task> findByDuedateBefore(Date date);
}
