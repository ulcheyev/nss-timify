package com.kyki.taskmicroservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskCreationRequest {

    private String name;
    private String description;
    private Long userId;
    private Long projectId;
    private List<CategoryDto> categoryDtoList;

}
