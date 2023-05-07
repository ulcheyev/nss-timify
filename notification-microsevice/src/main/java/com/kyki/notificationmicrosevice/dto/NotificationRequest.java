package com.kyki.notificationmicrosevice.dto;


import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest
{
    Long userId;
    Long id;
    String text;
    String email;

 /* Neco dalsiho */
}
