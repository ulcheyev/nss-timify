package com.kyki.taskmicroservice.repository;

import com.kyki.taskmicroservice.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectsByNameLike(String name);
}
