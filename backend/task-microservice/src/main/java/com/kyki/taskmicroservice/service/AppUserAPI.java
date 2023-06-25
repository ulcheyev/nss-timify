package com.kyki.taskmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AppUserAPI {

    private final RestTemplate restTemplate;

    public  Boolean isUser(Long userId) {
        return restTemplate.getForObject(
                "http://user-service/api/system/v1/users/is-user?userId={userId}",
                Boolean.class
        );
    }

    public  Boolean isAdmin(Long userId) {
        return restTemplate.getForObject(
                "http://user-service/api/v1/system/users/is-admin?userId={userId}",
                Boolean.class,
                userId
        );
    }
    public Boolean isExists(Long userId) {
        return restTemplate.getForObject(
                "http://user-service/api/v1/system/users/is-exists?userId={userId}",
                Boolean.class,
                userId
        );
    }

    public Boolean isExists(String name) {
        return restTemplate.getForObject(
                "http://user-service/api/v1/system/users/is-exists?userName={name}",
                Boolean.class,
                name
        );
    }

}
