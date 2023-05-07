package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.exception.ArgumentException;
import com.kyki.taskmicroservice.exception.NotFoundException;
import com.kyki.taskmicroservice.model.Statistic;
import com.kyki.taskmicroservice.model.Task;
import com.kyki.taskmicroservice.repository.StatisticRepository;
import com.kyki.taskmicroservice.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final TaskRepository taskRepository;


    @Override
    @Transactional
    public void addTask(@NonNull Long id, @NonNull Long taskId) {
        Statistic statistic = findById(id);
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task with id " + taskId + "does not exist."));
        statistic.addTask(task);
    }

    @Override
    public void removeTask(@NonNull Long id, @NonNull Long taskId) {
        Statistic statistic = findById(id);
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task with id " + taskId + "does not exist."));
        statistic.removeTask(task);
    }

    @Override
    public void changeStartTime(@NonNull Long id, @NonNull OffsetDateTime startTime) {
        Statistic statistic = findById(id);
        if (statistic.getTimeTill().isBefore(startTime)) {
            throw new ArgumentException("Start time can not be after end time.");
        }
        statistic.setTimeFrom(startTime);
    }

    @Override
    public void changeTillTime(@NonNull Long id, @NonNull OffsetDateTime tillTime) {
        Statistic statistic = findById(id);
        if (statistic.getTimeFrom().isBefore(tillTime)) {
            throw new ArgumentException("Time till can not be before start time.");
        }
        statistic.setTimeFrom(tillTime);
    }

    @Override
    public void save(Statistic statistic) {
        statisticRepository.save(statistic);
    }

    @Override
    public List<Statistic> findAll() {
        return statisticRepository.findAll();
    }

    @Override
    public void addTasks(@NonNull Long id, @NonNull List<Long> taskId) {
        if (taskId == null) {
            throw new ArgumentException("Task list can not be empty.");
        }
        for (Long singleTaskId : taskId) {
            addTask(id, singleTaskId);
        }
    }

    @Override
    public Statistic findById(@NonNull Long id) {
        return statisticRepository.findById(id).orElseThrow(() -> new NotFoundException("Statistics with id " + id + "does not exist."));
    }

    @Override
    public List<Statistic> findByUserId(@NonNull Long userId) {
        return statisticRepository.findByUserId(userId);
    }

    @Override
    public void deleteById(@NonNull Long id) {
        Statistic statistic = findById(id);
        statisticRepository.delete(statistic);
    }

    @Override
    public void deleteByUserId(@NonNull Long userId) {
        List<Statistic> statisticList = statisticRepository.findByUserId(userId);
        for (Statistic statistic : statisticList) {
            statisticRepository.delete(statistic);
        }
    }
}
