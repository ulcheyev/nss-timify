package com.kyki.taskmicroservice.utils;

import com.kyki.taskmicroservice.dto.CategoryDto;
import com.kyki.taskmicroservice.dto.ProjectDto;
import com.kyki.taskmicroservice.dto.TaskDto;
import com.kyki.taskmicroservice.model.Category;
import com.kyki.taskmicroservice.model.Project;
import com.kyki.taskmicroservice.model.Status;
import com.kyki.taskmicroservice.model.Task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Task toTask(TaskDto task, Project proj, List<Category> categories) {
        ZoneId zoneId = ZoneId.of("UTC");
        ZoneId defaultZone = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(task.getDeadline(), formatter);
        ZoneOffset offset = zoneId.getRules().getOffset(dateTime);
        OffsetDateTime offsetDeadline = OffsetDateTime.of(dateTime, offset);


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
                .deadline(offsetDeadline)
                .timeSpent(Duration.ZERO)
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
                .deadline(task.getDeadline() == null?"":task.getDeadline().toString())
                .timeSpent(task.getTimeSpent()==null?"": Long.toString(task.getTimeSpent().getSeconds()))
                .status(task.getStatus().toString())
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
