package com.kyki.taskmicroservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectTimeDto
{
    private String name;
    private String seconds;
}
