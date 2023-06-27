package com.kyki.usermicroservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {
    private String  username;
    private String  password;
    private String  email;
}
