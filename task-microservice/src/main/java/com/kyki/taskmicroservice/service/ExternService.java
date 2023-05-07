package com.kyki.taskmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExternService {

    private final RestTemplate restTemplate;

    public  Boolean isUser(Long userId) {
        return restTemplate.getForObject(
                "http://localhost:8080/api/system/users/isUser?userId={userId}",
                Boolean.class,
                userId
        );
    }

    public  Boolean isAdmin(Long userId) {
        return restTemplate.getForObject(
                "http://localhost:8080/api/system/users/isAdmin?userId={userId}",
                Boolean.class,
                userId
        );
    }

    public  Boolean isExists(Long userId) {
        return restTemplate.getForObject(
                "http://localhost:8080/api/system/users/isExists?userId={userId}",
                Boolean.class,
                userId
        );
    }

}
