package com.kyki.taskmicroservice.utils;

import com.kyki.taskmicroservice.dto.TaskCreationRequest;
import com.kyki.taskmicroservice.model.Category;
import com.kyki.taskmicroservice.model.Project;
import com.kyki.taskmicroservice.model.Status;
import com.kyki.taskmicroservice.model.Task;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Task toTask(TaskCreationRequest task, Project proj, List<Category> categories) {
        return Task
                .builder()
                .name(task.getName())
                .userId(task.getUserId())
                .project(proj)
                .description(task.getDescription())
                .categories(categories)
                .subtasks(new ArrayList<>())
                .startTime(OffsetDateTime.now())
                .status(Status.ACTIVE)
                .build();
    }
}
