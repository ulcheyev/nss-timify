package com.kyki.usermicroservice.dto;

import lombok.Data;

@Data
public class AppUserUpdateRequest {
    private String username;
    private String email;
}
