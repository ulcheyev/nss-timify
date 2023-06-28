package com.kyki.usermicroservice.utils;

import com.kyki.usermicroservice.dto.RegistrationRequest;
import com.kyki.usermicroservice.model.AppUser;
import com.kyki.usermicroservice.model.Role;

public class Mapper {
    public static AppUser toAppUser(RegistrationRequest request) {
        return AppUser.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();
    }

}
