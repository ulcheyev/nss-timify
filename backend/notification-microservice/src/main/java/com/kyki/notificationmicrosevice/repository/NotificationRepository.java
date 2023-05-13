package com.kyki.notificationmicrosevice.repository;

import com.kyki.notificationmicrosevice.model.Notification;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>
{
    Optional<Notification> findNotificationById(@NonNull Long id);
    List<Notification> findByUserId(Long userId);
}
