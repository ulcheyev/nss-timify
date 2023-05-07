package com.kyki.taskmicroservice.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "iconlink", nullable = false)
    private String iconLink = "shrek.png";


}
