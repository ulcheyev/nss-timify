package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.dto.TaskCreationRequest;
import com.kyki.taskmicroservice.model.Project;
import com.kyki.taskmicroservice.model.Task;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService
{
    List<Task> findAll();
    Task findById(@NonNull Long id);
    Task save(@NonNull TaskCreationRequest task);

    void deleteById(@NonNull Long taskId);

    void addSubtask(@NonNull Long taskId, @NonNull Long subtaskId);
    void removeSubtask(@NonNull Long taskId, @NonNull Long subtaskId);

    void addCategory(@NonNull Long taskId, @NonNull Long categoryId);
    void removeCategory(@NonNull Long taskId, @NonNull Long categoryId);

    @Transactional
    Task update(@NonNull Long id, String name, String description, Long projectId);
}
