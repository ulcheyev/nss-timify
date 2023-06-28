package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.dto.CategoryDto;
import com.kyki.taskmicroservice.dto.TaskDto;
import com.kyki.taskmicroservice.exception.ArgumentException;
import com.kyki.taskmicroservice.exception.NotFoundException;
import com.kyki.taskmicroservice.model.Category;
import com.kyki.taskmicroservice.model.Project;
import com.kyki.taskmicroservice.model.Task;
import com.kyki.taskmicroservice.repository.TaskRepository;
import com.kyki.taskmicroservice.utils.JwtUtils;
import com.kyki.taskmicroservice.utils.Mapper;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final CategoryService categoryService;
    private final AppUserAPI externService;

    @Override
    public List<TaskDto> findAll(int page, int size) {
        List<Task> all = taskRepository.findAll(PageRequest.of(page, size)).getContent();
        List<TaskDto> taskDtos = new ArrayList<>();
        for(Task task: all) {
            taskDtos.add(Mapper.toTaskDto(task));
        }
        return taskDtos;
    }

    @Override
    public List<TaskDto> findAllByUsername(String token) {
        String usernameFromToken = JwtUtils.getUsernameFromToken(token);
        System.out.println(usernameFromToken);
        List<Task> all = taskRepository.findTaskByOwner(usernameFromToken);
        List<TaskDto> taskDtos = new ArrayList<>();
        for(Task task: all) {
            taskDtos.add(Mapper.toTaskDto(task));
        }
        return taskDtos;
    }


    @Override
    public Task findById(@NonNull Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task with id " + id + " does not exist."));
        String user = task.getOwner();
        if(!externService.isExists(user)) {
           throw new NotFoundException("User with name " + user +" does not exists");
        }
        return task;

    }

    @Override
    public TaskDto findTaskDtoById(@NonNull Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task with id " + id + " does not exist."));
        String user = task.getOwner();
        if(!externService.isExists(user)) {
            throw new NotFoundException("User with name " + user +" does not exists");
        }
        return Mapper.toTaskDto(task);
    }

    @Override
    public Task save(@NonNull TaskDto task) {
        log.info("TaskService-save: " + task);
        projectService.findById(task.getProjectId());
        List<Category> categories = new ArrayList<>();
        for(CategoryDto cat: task.getCategoryDtoList()) {
            Category byId = categoryService.findById(cat.getId());
            categories.add(byId);
        }
        String user = task.getUser();
        if(!externService.isExists(user)) {
            throw new NotFoundException("User with name " + user +" does not exists");
        }
        Task task1 = Mapper.toTask(task, projectService.findById(task.getProjectId()), categories);
        return taskRepository.save(task1);
    }

    @Override
    public void deleteById(@NonNull Long taskId) {
        Task task = findById(taskId);
        taskRepository.delete(task);
    }

    @Override
    public void addSubtask(@NonNull Long taskId, @NonNull Long subtaskId) {
        Task task = findById(taskId);
        Task subTask = findById(subtaskId);
        task.addSubtask(subTask);
    }

    @Override
    public void removeSubtask(@NonNull Long taskId, @NonNull Long subtaskId) {
        Task task = findById(taskId);
        Task subTask = findById(subtaskId);
        task.removeSubtask(subTask);
    }

    @Override
    public void addCategory(@NonNull Long taskId, @NonNull Long categoryId) {
        Task task = findById(taskId);
        Category category = categoryService.findById(categoryId);
        task.addCategory(category);
    }
    @Override
    public void removeCategory(@NonNull Long taskId, @NonNull Long categoryId) {
        Task task = findById(taskId);
        Category category = categoryService.findById(categoryId);
        task.removeCategory(category);
    }

    @Override
    @Transactional
    public void startTask(@NonNull Long taskId) {
        Task task = findById(taskId);
        task.startTask();
    }

    @Override
    @Transactional
    public void stopTask(@NonNull Long taskId) {
        Task task = findById(taskId);
        task.stopTask();
    }


    @Override
    @Transactional
    public Task update(@NonNull Long id, String name, String description, Long projectId) {
        Task task = findById(id);
        if(projectId!=null) {
            Project project = projectService.findById(id);
            project.addTask(task);
        }
        if(description!=null)
        {
            if(description.length()<=1)
            {
                throw new ArgumentException("Description " + description + " is too short.");
            }
            else {
                task.setDescription(description);
            }
        }
        if(name!=null)
        {
            if(name.length()<=1)
            {
                throw new ArgumentException("Name " + name + " is too short.");
            }
            else {
                task.setName(name);
            }
        }
        return task;
    }



}
