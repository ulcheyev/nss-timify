package com.kyki.taskmicroservice.controller;

import com.kyki.taskmicroservice.dto.CategoryCreateRequest;
import com.kyki.taskmicroservice.model.Category;
import com.kyki.taskmicroservice.service.CategoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id)
    {
        return categoryService.findById(id);
    }

    @GetMapping
    public List<Category> getCategories()
    {
        return categoryService.findAll();
    }

    @GetMapping("/name/{name}")
    public Category getCategory(String name)
    {
        return categoryService.findByName(name);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody @NonNull CategoryCreateRequest request)
    {
        categoryService.save(request);
        return new ResponseEntity<>("Category is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(Long id)
    {
        categoryService.deleteById(id);
        return new ResponseEntity<>("Category is deleted successfully", HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<String> updateCategory(Long id,@RequestBody(required = false) String name,@RequestBody(required = false) String description,@RequestBody(required = false) String iconLink)
    {
        categoryService.update(id, name, description, iconLink);
        return new ResponseEntity<>("Category is updated successfully", HttpStatus.OK);

    }
}
