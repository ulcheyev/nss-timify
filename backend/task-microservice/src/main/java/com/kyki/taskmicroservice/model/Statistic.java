package com.kyki.taskmicroservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Statistic {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private OffsetDateTime timeFrom;
    @Column(nullable = false)
    private OffsetDateTime timeTill;
    @OneToMany
    private List<Task> tasks;
    @Column(nullable = false)
    private long userId;

    public void addTask(Task task)
    {
        tasks.add(task);
    }

    public void removeTask(Task task)
    {
        tasks.remove(task);
    }

}
