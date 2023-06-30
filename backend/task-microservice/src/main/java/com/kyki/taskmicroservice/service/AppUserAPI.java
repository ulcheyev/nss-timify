package com.kyki.taskmicroservice.service;

import com.kyki.taskmicroservice.dto.IsExistsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AppUserAPI {

    private final RestTemplate restTemplate;

    public  Boolean isUser(String token) {
        String cleanToken = token.replace("Bearer ", "");
        return Objects.requireNonNull(restTemplate.getForObject(
                "http://user-service/api/system/v1/users/is-user?tok=" + cleanToken,
                IsExistsResponse.class,
                token
        )).getEx();
    }

    public  Boolean isAdmin(String token) {
        String cleanToken = token.replace("Bearer ", "");
        return Objects.requireNonNull(restTemplate.getForObject(
                "http://user-service/api/v1/system/users/is-admin?tok=" + cleanToken,
                IsExistsResponse.class,
                token
        )).getEx();
    }

    public Boolean isExists(String name) {
        return Objects.requireNonNull(restTemplate.getForObject(
                "http://user-service/api/v1/system/users/is-exists?userName={name}",
                IsExistsResponse.class,
                name
        )).getEx();
    }

}
