package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.controller.CategoryController;
import com.kyki.taskmicroservice.dto.CategoryCreateRequest;
import com.kyki.taskmicroservice.model.Category;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(@NonNull Long id);

    Category findByName(@NonNull String name);

    Category save(@NonNull CategoryCreateRequest category);

    void deleteById(@NonNull Long id);

    @Transactional
    Category update(@NonNull Long id, String name, String description, String iconLink);
}
