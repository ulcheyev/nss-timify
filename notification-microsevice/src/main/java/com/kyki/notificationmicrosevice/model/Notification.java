package com.kyki.notificationmicrosevice.model;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Notification {
    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long userId;
    private String text;

}
