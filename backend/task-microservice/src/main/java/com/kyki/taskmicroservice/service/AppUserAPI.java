package com.kyki.taskmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AppUserAPI {

    private final RestTemplate restTemplate;

    public  Boolean isUser(String token) {
        String cleanToken = token.replace("Bearer ", "");
        return restTemplate.getForObject(
                "http://user-service/api/system/v1/users/is-user?tok="+cleanToken,
                Boolean.class,
                token
        );
    }

    public  Boolean isAdmin(String token) {
        String cleanToken = token.replace("Bearer ", "");
        return restTemplate.getForObject(
                "http://user-service/api/v1/system/users/is-admin?tok="+cleanToken,
                Boolean.class,
                token
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
