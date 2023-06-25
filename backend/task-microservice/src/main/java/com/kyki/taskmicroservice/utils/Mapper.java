package com.kyki.taskmicroservice.utils;

import com.kyki.taskmicroservice.dto.CategoryDto;
import com.kyki.taskmicroservice.dto.ProjectDto;
import com.kyki.taskmicroservice.dto.TaskDto;
import com.kyki.taskmicroservice.model.Category;
import com.kyki.taskmicroservice.model.Project;
import com.kyki.taskmicroservice.model.Status;
import com.kyki.taskmicroservice.model.Task;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Task toTask(TaskDto task, Project proj, List<Category> categories) {
        return Task
                .builder()
                .name(task.getName())
                .owner(task.getUser())
                .project(proj)
                .description(task.getDescription())
                .categories(categories)
                .subtasks(new ArrayList<>())
                .startTime(OffsetDateTime.now())
                .status(Status.ACTIVE)
                .build();
    }

    public static TaskDto toTaskDto(Task task) {

        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for(var cat: task.getCategories())
        {
            CategoryDto e = new CategoryDto();
            e.setId(cat.getCategoryId());
            categoryDtoList.add(e);
        }

        return TaskDto
                .builder()
                .id(task.getTaskId())
                .name(task.getName())
                .user(task.getOwner())
                .projectId(task.getProject().getId())
                .description(task.getDescription())
                .categoryDtoList(categoryDtoList)
                .build();
    }

    public static ProjectDto toProjectDto(Project project) {

        List<TaskDto> taskDtoList = new ArrayList<>();

        for(var task: project.getTasks())
        {
            taskDtoList.add(toTaskDto(task));
        }

        return ProjectDto
                .builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .taskDtos(taskDtoList)
                .build();
    }
}
