package com.kyki.notificationmicrosevice.service;

import com.kyki.notificationmicrosevice.dto.NotificationRequest;
import com.kyki.notificationmicrosevice.exception.NotFoundException;
import com.kyki.notificationmicrosevice.model.EmailNotification;
import com.kyki.notificationmicrosevice.model.Notification;
import com.kyki.notificationmicrosevice.notificationmethod.EmailSend;
import com.kyki.notificationmicrosevice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService
{
    private final NotificationRepository notificationRepository;

    @Override
    public Notification getNotification(Long id) {
        return notificationRepository.findNotificationById(id).orElseThrow(() -> new NotFoundException("Notification with id " + id + " does not exist."));
    }

    @Override
    public List<Notification> getUsersNotifications(Long userId)
    {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public void deleteById(Long id)
    {
        Notification notification = getNotification(id);
        notificationRepository.delete(notification);
    }

    @Override
    public void sendNotification(NotificationRequest notificationRequest)
    {


        if(Objects.equals(notificationRequest.getText(), "greetings")) {
            notificationRequest.setText("Welcome to TIMIFY!");
        }

        EmailNotification notification = new EmailNotification
                (notificationRequest.getEmail());
        notification.setText(notificationRequest.getText());
        notification.setUserId(notificationRequest.getUserId());

        EmailSend.config();
        EmailSend.send(notificationRequest.getText(), notificationRequest.getEmail());

        notificationRepository.save(notification);
    }

}
