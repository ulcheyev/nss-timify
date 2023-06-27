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
    List<TaskDto> findAll();

    public List<TaskDto> findAllByUsername(@NonNull String username);

    TaskDto findTaskDtoById(@NonNull Long id);

    Task findById(@NonNull Long id);

    Task save(@NonNull TaskDto task);

    void deleteById(@NonNull Long taskId);

    void addSubtask(@NonNull Long taskId, @NonNull Long subtaskId);

    void removeSubtask(@NonNull Long taskId, @NonNull Long subtaskId);

    void addCategory(@NonNull Long taskId, @NonNull Long categoryId);

    void removeCategory(@NonNull Long taskId, @NonNull Long categoryId);


    @Transactional
    Task update(@NonNull Long id, String name, String description, Long projectId);
}
