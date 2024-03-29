package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.dto.TaskDto;
import com.kyki.taskmicroservice.model.Task;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService
{
    List<TaskDto> findAll(int page, int size);

    public List<TaskDto> findAllByUsernameArchived(int page, int size, @NonNull String username);
    public List<TaskDto> findAllByUsername(@NonNull int page, @NonNull int size, @NonNull String username);

    TaskDto findTaskDtoById(@NonNull Long id);

    Task findById(@NonNull Long id);

    Task save(@NonNull TaskDto task);

    void deleteById(@NonNull Long taskId);

    void addSubtask(@NonNull Long taskId, @NonNull Long subtaskId);

    void removeSubtask(@NonNull Long taskId, @NonNull Long subtaskId);

    void addCategory(@NonNull Long taskId, @NonNull Long categoryId);

    void removeCategory(@NonNull Long taskId, @NonNull Long categoryId);

    void startTask(@NonNull Long taskId);
    void stopTask(@NonNull Long taskId);

    void archiveTask(@NonNull Long taskId);


    @Transactional
    Task update(@NonNull Long id, String name, String description, Long projectId);

    List<TaskDto> findByName(String name);

    Long getTasksCount(String token);
}
