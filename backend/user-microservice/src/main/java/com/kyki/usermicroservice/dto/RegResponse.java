package com.kyki.usermicroservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegResponse {
    private Long id;
    private String token;
}
