package com.kyki.taskmicroservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private String user;
    private Long projectId;
    private List<CategoryDto> categoryDtoList;

}
