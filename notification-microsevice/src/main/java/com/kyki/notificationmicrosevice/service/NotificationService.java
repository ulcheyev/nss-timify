package com.kyki.notificationmicrosevice.service;

import com.kyki.notificationmicrosevice.dto.NotificationRequest;
import com.kyki.notificationmicrosevice.model.Notification;

import java.util.List;

public interface NotificationService {
    Notification getNotification(Long id);

    List<Notification> getUsersNotifications(Long userId);

    void deleteById(Long id);

    void sendNotification(NotificationRequest notificationRequest);
}
