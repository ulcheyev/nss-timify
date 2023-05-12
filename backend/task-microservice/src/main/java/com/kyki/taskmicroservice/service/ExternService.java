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
                "https://user-microservice-386100.uc.r.appspot.com/api/system/v1/users/isUser?userId={userId}",
                Boolean.class,
                userId
        );
    }

    public  Boolean isAdmin(Long userId) {
        return restTemplate.getForObject(
                "https://user-microservice-386100.uc.r.appspot.com/api/system/v1/users/isAdmin?userId={userId}",
                Boolean.class,
                userId
        );
    }
    public  Boolean isExists(Long userId) {
        return restTemplate.getForObject(
                "https://user-microservice-386100.uc.r.appspot.com/api/system/v1/users/isExists?userId={userId}",
                Boolean.class,
                userId
        );
    }

}
