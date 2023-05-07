package com.kyki.usermicroservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Builder
public class RegistrationRequest {
    private String  username;
    private String  password;
    private String  email;
}
