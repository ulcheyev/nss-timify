package com.kyki.taskmicroservice.dto;

import lombok.Data;

@Data
public class CategoryCreateRequest {
    private String name;
    private String description;
    private String iconLink;
}
