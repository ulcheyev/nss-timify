package com.kyki.taskmicroservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectDto {
    private Long id;
    private  String name;
    private String description;
    private List<TaskDto> taskDtos;
}
