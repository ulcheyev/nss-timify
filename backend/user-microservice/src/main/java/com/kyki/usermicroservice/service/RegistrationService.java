package com.kyki.usermicroservice.service;

import com.kyki.usermicroservice.dto.AuthResponse;
import com.kyki.usermicroservice.dto.RegResponse;
import com.kyki.usermicroservice.dto.RegistrationRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;

public interface RegistrationService {
    public RegResponse register(@RequestBody @NonNull RegistrationRequest request);
}
