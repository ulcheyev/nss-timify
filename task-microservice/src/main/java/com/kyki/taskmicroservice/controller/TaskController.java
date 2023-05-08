package com.kyki.taskmicroservice.controller;

import com.kyki.taskmicroservice.dto.CategoryDto;
import com.kyki.taskmicroservice.dto.TaskCreationRequest;
import com.kyki.taskmicroservice.model.Task;
import com.kyki.taskmicroservice.service.CategoryService;
import com.kyki.taskmicroservice.service.ProjectService;
import com.kyki.taskmicroservice.service.StatisticService;
import com.kyki.taskmicroservice.service.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/task")
public class TaskController
{
    private final TaskService taskService;
    private final ProjectService projectService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id)
    {
        return taskService.findById(id);
    }

    @GetMapping
    public List<Task> getTask()
    {
        return taskService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody @NonNull TaskCreationRequest taskCreationRequest)
    {
        taskService.save(taskCreationRequest);
        return new ResponseEntity<>("Task is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(Long id)
    {
        taskService.deleteById(id);
        return new ResponseEntity<>("Task is deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/addsubtask")
    public ResponseEntity<String> addSubtask(Long taskId, Long subtaskId)
    {
        taskService.addSubtask(taskId, subtaskId);
        return new ResponseEntity<>("Subtask added successfully", HttpStatus.OK);
    }

    @PutMapping("/removesubtask")
    public ResponseEntity<String> removeSubtask(Long taskId, Long subtaskId)
    {
        taskService.removeSubtask(taskId, subtaskId);
        return new ResponseEntity<>("Subtask removed successfully", HttpStatus.OK);
    }

    @PutMapping("/addcategory")
    public ResponseEntity<String> addCategory(Long taskId, Long categoryId)
    {
        taskService.addSubtask(taskId, categoryId);
        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }

    @PutMapping("/removecategory")
    public ResponseEntity<String> removeCategory(Long taskId, Long categoryId)
    {
        taskService.removeSubtask(taskId, categoryId);
        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }
}

