package com.kyki.taskmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE)
    private Long taskId;

    @Column
    private String description;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private OffsetDateTime startTime = OffsetDateTime.now();

    @Column
    private OffsetDateTime deadline;
    @Column
    @NonNull
    private Duration timeSpent;

    @OneToMany
    private List<Task> subtasks;

    @ManyToOne
    private Project project;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "tasks_categories",
            joinColumns =
            @JoinColumn(name = "taskId"),
            inverseJoinColumns =
            @JoinColumn(name = "categoryId")
    )
    private List<Category> categories;


    @Column(nullable = false)
    private String owner;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public void startTask() {
        status = Status.ACTIVE;
        startTime = OffsetDateTime.now();
    }

    public void stopTask()
    {
        status = Status.STOPPED;
        System.out.println("Stopped task" + taskId.toString());
        if (timeSpent == null) timeSpent=Duration.ZERO;
        System.out.println(timeSpent);
        Duration dur = Duration.between(startTime,OffsetDateTime.now());
        timeSpent = timeSpent.plus(dur);
    }


    public void addSubtask(Task subtask) {
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
