package com.kyki.taskmicroservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void clearTasks() {
        tasks.clear();
    }

}
