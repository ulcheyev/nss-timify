package com.kyki.taskmicroservice.controller;

import com.kyki.taskmicroservice.dto.TaskDto;
import com.kyki.taskmicroservice.exception.ArgumentException;
import com.kyki.taskmicroservice.model.Task;
import com.kyki.taskmicroservice.service.AppUserAPI;
import com.kyki.taskmicroservice.service.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/core/tasks")
public class TaskController
{

    private final AppUserAPI appUserAPI;
    private final TaskService taskService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok().body(taskService.findById(id));
    }

    @GetMapping("/name/{name}")
    public List<TaskDto> getTaskByNameLike(@NonNull @PathVariable("name") String name)
    {
        return taskService.findByName(name);
    }

    @GetMapping("/all")
    @CachePut(value = "tasks")
    public List<TaskDto> getTasksAll(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        if(appUserAPI.isAdmin(token)){
            return taskService.findAll(page, size);
        }
        throw new ArgumentException("Not allowed");

    }

    @GetMapping
    public List<TaskDto> getTasks(@RequestHeader("Authorization") String token,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size)
    {
        return taskService.findAllByUsername(page, size, token);
    }

    @GetMapping(value = "/archived")
    public List<TaskDto> getTasksArchived(@RequestHeader("Authorization") String token,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size)
    {
        return taskService.findAllByUsernameArchived(page, size,token);
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody @NonNull TaskDto taskCreationRequest)
    {
        taskService.save(taskCreationRequest);
        return new ResponseEntity<>("Task is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CacheEvict("tasks")
    public ResponseEntity<String> deleteTask(Long id)
    {
        taskService.deleteById(id);
        return new ResponseEntity<>("Task is deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/add-subtask")
    public ResponseEntity<String> addSubtask(Long taskId, Long subtaskId)
    {
        taskService.addSubtask(taskId, subtaskId);
        return new ResponseEntity<>("Subtask added successfully", HttpStatus.OK);
    }

    @PutMapping("/remove-subtask")
    public ResponseEntity<String> removeSubtask(Long taskId, Long subtaskId)
    {
        taskService.removeSubtask(taskId, subtaskId);
        return new ResponseEntity<>("Subtask removed successfully", HttpStatus.OK);
    }

    @PutMapping("/add-category")
    public ResponseEntity<String> addCategory(Long taskId, Long categoryId)
    {
        taskService.addSubtask(taskId, categoryId);
        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }

    @PutMapping("/remove-category")
    public ResponseEntity<String> removeCategory(Long taskId, Long categoryId)
    {
        taskService.removeSubtask(taskId, categoryId);
        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }

    @PutMapping("/start-task")
    public ResponseEntity<String> startTask(@RequestBody Long taskId)
    {
        taskService.startTask(taskId);
        return new ResponseEntity<String>("Task started successfully", HttpStatus.OK);

    }
    @PutMapping("/stop-task")
    public ResponseEntity<String> stopTask(@RequestBody Long taskId)
    {
        taskService.stopTask(taskId);
        return new ResponseEntity<String>("Task stopped successfully", HttpStatus.OK);

    }

    @PutMapping("/archive-task")
    public ResponseEntity<String> archiveTask(@RequestBody Long taskId)
    {
        taskService.archiveTask(taskId);
        return new ResponseEntity<String>("Task archived successfully", HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> getTasksCount(@RequestHeader("Authorization") String token)
    {
        return ResponseEntity.ok(taskService.getTasksCount(token));
    }
}

