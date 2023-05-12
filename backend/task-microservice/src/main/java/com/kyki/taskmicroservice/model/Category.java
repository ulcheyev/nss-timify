package com.kyki.taskmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category
{
    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE)
    private Long categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "iconlink", nullable = false)
    private String iconLink = "shrek.png";

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<Task> tasks;

}
