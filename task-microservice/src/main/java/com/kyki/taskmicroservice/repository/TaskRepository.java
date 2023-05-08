package com.kyki.taskmicroservice.repository;

import com.kyki.taskmicroservice.model.Task;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>
{
    Optional<Task> findById(@NonNull Long id);
}
