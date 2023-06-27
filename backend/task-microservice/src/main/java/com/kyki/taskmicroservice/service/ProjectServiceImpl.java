package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.dto.ProjectDto;
import com.kyki.taskmicroservice.dto.TaskDto;
import com.kyki.taskmicroservice.exception.ArgumentException;
import com.kyki.taskmicroservice.exception.NotFoundException;
import com.kyki.taskmicroservice.model.Project;
import com.kyki.taskmicroservice.model.Task;
import com.kyki.taskmicroservice.repository.ProjectRepository;
import com.kyki.taskmicroservice.repository.TaskRepository;
import com.kyki.taskmicroservice.utils.Mapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<ProjectDto> findAllProjectDto() {
        List<Project> all = projectRepository.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for(Project project: all) {
            projectDtos.add(Mapper.toProjectDto(project));
        }
        return projectDtos;
    }
    @Override
    public List<TaskDto> findTasksDTOByProjectId(@NonNull Long id)
    {
        return findById(id).getTasks().stream().map(task -> Mapper.toTaskDto(task)).collect(Collectors.toList());
    }


    @Override
    public Project findById(@NonNull Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project with id " + id + " does not exist."));
    }

    @Override
    public List<Project> findByName(@NonNull String name) {
        return projectRepository.findProjectsByNameLike(name);
    }

    @Override
    public Project save(@NonNull Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void addTask(Long id, Long taskId) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project with id " + id + " does not exist."));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task with id " + id + " does not exist."));
        task.setProject(project);
        project.addTask(task);
    }

    @Override
    public void removeTask(Long id, Long taskId) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project with id " + id + " does not exist."));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task with id " + id + " does not exist."));
        task.setProject(project);
        project.removeTask(task);
    }

    @Override
    public void deleteById(Long id) {
        Project project = findById(id);
        projectRepository.delete(project);
    }

    @Override
    public Project update(@NonNull Long id, String name, String description, List<Long> tasks) {
        Project project = findById(id);
        if (description != null) {
            if (description.length() <= 1) {
                throw new ArgumentException("Description " + description + " is too short.");
            } else {
                project.setDescription(description);
            }
        }
        if (name != null) {
            if (name.length() <= 1) {
                throw new ArgumentException("Name " + name + " is too short.");
            } else {
                project.setName(name);
            }
        }
        if (tasks != null) {
            project.clearTasks();
            Task task;
            for (Long taskId : tasks) {
                task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task with id " + id + " does not exist."));
                project.addTask(task);
            }
        }
        return project;
    }
}
