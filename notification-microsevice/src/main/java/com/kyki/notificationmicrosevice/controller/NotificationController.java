package com.kyki.notificationmicrosevice.controller;


import com.kyki.notificationmicrosevice.dto.NotificationRequest;
import com.kyki.notificationmicrosevice.model.Notification;
import com.kyki.notificationmicrosevice.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/project")
public class NotificationController
{
    private final EmailNotificationService emailNotificationService;

    @GetMapping(value = "/user/{id}")
    List<Notification> getByUserId(@RequestParam Long id)
    {
        return emailNotificationService.getUsersNotifications(id);
    }

    @GetMapping(value = "/{id}")
    Notification getNotification(@RequestParam Long id)
    {
        return emailNotificationService.getNotification(id);
    }

    @PostMapping(value = "/email")
    ResponseEntity<String> sendNotification(NotificationRequest notificationRequest)
    {
        emailNotificationService.sendNotification(notificationRequest);
        return new ResponseEntity<>("Notification sent!!!", HttpStatus.OK);
    }
}
