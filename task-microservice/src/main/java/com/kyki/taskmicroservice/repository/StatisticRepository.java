package com.kyki.taskmicroservice.repository;

import com.kyki.taskmicroservice.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    List<Statistic> findByUserId(Long id);
}
