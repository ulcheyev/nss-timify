package com.kyki.taskmicroservice.controller;

import com.kyki.taskmicroservice.model.Statistic;
import com.kyki.taskmicroservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/statistics")
public class StatisticController {
    private final StatisticService statisticService;
    @GetMapping("/{id}")
    public Statistic getStatistic(@PathVariable Long id)
    {
        return statisticService.findById(id);
    }

    @GetMapping
    public List<Statistic> getStatistic()
    {
        return statisticService.findAll();
    }


    @PostMapping
    public ResponseEntity<String> createStatistic(Long userId,
                                                  OffsetDateTime timeFrom,
                                                  OffsetDateTime timeTill,
                                                  @RequestBody(required = false) List<Long> taskList)
    {
        Statistic statistic = Statistic.builder().userId(userId).timeFrom(timeFrom).timeTill(timeTill).build();
        statisticService.save(statistic);
        if(taskList!=null)
        {
            statisticService.addTasks(statistic.getId(), taskList);
        }
        return new ResponseEntity<>("Statistic is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStatistic(@PathVariable Long id)
    {
        statisticService.deleteById(id);
        return new ResponseEntity<>("Statistic is deleted successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}/addTask")
    public ResponseEntity<String> addTask(@PathVariable Long id, Long taskId)
    {
        statisticService.addTask(id, taskId);
        return new ResponseEntity<>("Task added successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}/removeTask")
    public ResponseEntity<String> removeTask(@PathVariable Long id, Long taskId)
    {
        statisticService.removeTask(id, taskId);
        return new ResponseEntity<>("Task removed successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}/startTime")
    public ResponseEntity<String> changeStartTime(@PathVariable Long id, OffsetDateTime startTime)
    {
        statisticService.changeStartTime(id, startTime);
        return new ResponseEntity<>("Start time changed successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{id}/tillTime")
    public ResponseEntity<String> changeTillTime(@PathVariable Long id, OffsetDateTime tillTime)
    {
        statisticService.changeStartTime(id, tillTime);
        return new ResponseEntity<>("Start time changed successfully", HttpStatus.OK);
    }

}
