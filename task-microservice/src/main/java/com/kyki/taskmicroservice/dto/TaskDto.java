package com.kyki.taskmicroservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private Long userId;
    private Long projectId;
    private List<CategoryDto> categoryDtoList;

}
