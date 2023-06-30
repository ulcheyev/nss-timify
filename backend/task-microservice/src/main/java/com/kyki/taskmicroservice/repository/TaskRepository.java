package com.kyki.taskmicroservice.repository;

import com.kyki.taskmicroservice.model.Status;
import com.kyki.taskmicroservice.model.Task;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>
{
    @NonNull Optional<Task> findById(@NonNull @Param("id") Long id);

    List<Task> findTaskByOwnerAndStatusIn(@Param("pageable") Pageable pageable, @NonNull @Param("owner") String owner, List<Status> statusList);

    List<Task> findAllByNameLike(@Param("name")  String name);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.owner=?1 AND t.status = 'ACTIVE'")
    Long getTasksCountByUsername(@Param("username") String username);

}
