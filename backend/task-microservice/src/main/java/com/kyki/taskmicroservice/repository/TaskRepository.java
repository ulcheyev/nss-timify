package com.kyki.taskmicroservice.repository;

import com.kyki.taskmicroservice.model.Project;
import com.kyki.taskmicroservice.model.Task;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>
{
    Optional<Task> findById(@NonNull Long id);

    List<Task> findTaskByOwner(@NonNull String owner);

    List<Task> findAllByNameLike(String name);

}
