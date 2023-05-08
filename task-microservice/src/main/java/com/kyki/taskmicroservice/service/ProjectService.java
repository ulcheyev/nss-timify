package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.dto.ProjectDto;
import com.kyki.taskmicroservice.model.Project;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    List<ProjectDto> findAllProjectDto();

    Project findById(@NonNull Long id);

    List<Project> findByName(@NonNull String name);

    Project save(@NonNull Project category);

    @Transactional
    void addTask(@NonNull Long id, @NonNull Long taskId);

    void removeTask(Long id, Long taskId);

    void deleteById(@NonNull Long id);

    @Transactional
    Project update(@NonNull Long id, String name, String description, List<Long> tasks);
}
