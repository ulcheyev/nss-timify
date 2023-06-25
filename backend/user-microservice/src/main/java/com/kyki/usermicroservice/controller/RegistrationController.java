package com.kyki.usermicroservice.controller;

import com.kyki.usermicroservice.dto.AuthResponse;
import com.kyki.usermicroservice.dto.RegistrationRequest;
import com.kyki.usermicroservice.service.RegistrationService;
import com.kyki.usermicroservice.utils.TimifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/system/register")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class RegistrationController
{
    private final RegistrationService registrationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> register(@RequestBody @NonNull RegistrationRequest request) {
        final HttpHeaders headers = TimifyUtils.createLocationHeaderFromCurrentUri("/api/login");
        log.info("RegistrationController-register: " + request);
        return ResponseEntity.ok().headers(headers).body(registrationService.register(request));
    }

}
