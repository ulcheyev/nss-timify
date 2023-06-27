package com.kyki.taskmicroservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private String user;
    private Long projectId;
    private List<CategoryDto> categoryDtoList;
    private String deadline;
    private String timeSpent;

}
