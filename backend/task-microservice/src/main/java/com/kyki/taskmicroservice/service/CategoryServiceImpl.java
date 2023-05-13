package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.dto.CategoryCreateRequest;
import com.kyki.taskmicroservice.exception.ArgumentException;
import com.kyki.taskmicroservice.exception.NotFoundException;
import com.kyki.taskmicroservice.exception.UpdateCollision;
import com.kyki.taskmicroservice.model.Category;
import com.kyki.taskmicroservice.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(@NonNull Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id " + id + " does not exist."));
    }

    @Override
    public Category findByName(@NonNull String name) {
        return categoryRepository.findByName(name).orElseThrow(() -> new NotFoundException("Category with name " + name + " does not exist."));

    }

    @Override
    public Category save(@NonNull CategoryCreateRequest category) {

        if (categoryRepository.findByName(category.getName()).isPresent())
        {
            throw new UpdateCollision("Category with name " + category.getName() + " already exists.");
        }

        if(category.getIconLink() == null) category.setIconLink("shrek.png");
        Category category1 = Category.builder().name(category.getName()).description(category.getDescription())
                .iconLink(category.getIconLink()).build();
        return categoryRepository.save(category1);
    }

    @Override
    public void deleteById(@NonNull Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }

    @Override
    @Transactional
    public Category update(@NonNull Long id, String name, String description, String iconLink) {
        Category cat = findById(id);
        if (name != null) {
            if (name.length() <= 1) {
                throw new ArgumentException("Name " + name + " is too short.");
            } else {
                if (!cat.equals(categoryRepository.findByName(name))) {
                    throw new UpdateCollision("Category with name " + name + " already exists.");
                }
                cat.setName(name);
            }
        }
        if (description != null) {
            if (description.length() <= 1) {
                throw new ArgumentException("Description " + description + " is too short.");
            } else {
                cat.setDescription(description);
            }
        }
        if (iconLink != null) {
            if (description.length() <= 1) {
                throw new ArgumentException("Icon link " + description + " is invalid.");
            } else {
                cat.setDescription(description);
            }
        }
        return cat;
    }
}
