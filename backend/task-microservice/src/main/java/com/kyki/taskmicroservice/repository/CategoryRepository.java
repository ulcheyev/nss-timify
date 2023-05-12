package com.kyki.taskmicroservice.repository;

import com.kyki.taskmicroservice.model.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
    Optional<Category> findById(@NonNull Long id);
    Optional<Category> findByName(String name);
}
