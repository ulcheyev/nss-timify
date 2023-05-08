package com.kyki.taskmicroservice.controller;

import com.kyki.taskmicroservice.model.Project;
import com.kyki.taskmicroservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id)
    {
        return projectService.findById(id);
    }

    @GetMapping
    public List<Project> getCategories()
    {
        return projectService.findAll();
    }

    @GetMapping("/name/{name}")
    public List<Project> getProject(String name)
    {
        return projectService.findByName(name);
    }

    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody(required = false) String description, String name, @RequestBody(required = false) String iconLink)
    {
        Project project = Project.builder().description(description).name(name).build();

        projectService.save(project);
        return new ResponseEntity<>("Project is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(Long id)
    {
        projectService.deleteById(id);
        return new ResponseEntity<>("Project is deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateProject(Long id,@RequestBody(required = false) String name,@RequestBody(required = false) String description,@RequestBody(required = false) List<Long> tasksList)
    {
        projectService.update(id, name, description, tasksList);
        return new ResponseEntity<>("Project is updated successfully", HttpStatus.OK);

    }

    @PutMapping(value = "/addtask")
    public ResponseEntity<String> addTask(Long id, Long taskId)
    {
        projectService.addTask(id, taskId);
        return new ResponseEntity<>("Task added successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/removetask")
    public ResponseEntity<String> removeTask(Long id, Long taskId)
    {
        projectService.removeTask(id, taskId);
        return new ResponseEntity<>("Task removed successfully", HttpStatus.OK);
    }
}
