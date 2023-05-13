package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.model.Statistic;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.List;

public interface StatisticService {
    void addTask(@NonNull Long id, @NonNull Long taskId);

    void removeTask(@NonNull Long id, @NonNull Long taskId);

    void changeStartTime(@NonNull Long id, @NonNull OffsetDateTime startTime);

    void changeTillTime(@NonNull Long id, @NonNull OffsetDateTime tillTime);

    void save(Statistic statistic);

    List<Statistic> findAll();

    void addTasks(@NonNull Long id, @NonNull List<Long> taskId);

    Statistic findById(@NonNull Long id);

    List<Statistic> findByUserId(@NonNull Long userId);

    void deleteById(@NonNull Long id);

    void deleteByUserId(@NonNull Long userId);
}
