package com.kyki.usermicroservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class AuthResponse {
    private String token;
}
