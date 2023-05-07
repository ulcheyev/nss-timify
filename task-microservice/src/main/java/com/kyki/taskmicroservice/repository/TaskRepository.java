package com.kyki.taskmicroservice.repository;

import com.kyki.taskmicroservice.model.Task;
import lombok.NonNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;

public interface TaskRepository extends JpaRepository<Task, Long>
{
    Optional<Task> findById(@NonNull Long id);
}
