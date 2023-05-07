package com.kyki.usermicroservice.service;

import com.kyki.usermicroservice.dto.AuthRequest;
import com.kyki.usermicroservice.dto.AuthResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    AuthResponse authenticate(@RequestBody @NonNull AuthRequest request);
}
