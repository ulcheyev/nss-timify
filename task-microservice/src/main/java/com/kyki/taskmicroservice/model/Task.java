package com.kyki.taskmicroservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task
{
    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String description;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private OffsetDateTime startTime = OffsetDateTime.now();
    @OneToMany
    private List<Task> subtasks;
    @ManyToOne
    private Project project;
    @ManyToMany
    private List<Category> categories;
    @Column(nullable = false)
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public void addSubtask(Task subtask)
    {
        subtasks.add(subtask);
    }

    public void removeSubtask(Task subtask)
    {
        subtasks.remove(subtask);
    }

    public void addCategory(Category category)
    {
        categories.add(category);
    }

    public void removeCategory(Category category)
    {
        categories.remove(category);
    }
}
