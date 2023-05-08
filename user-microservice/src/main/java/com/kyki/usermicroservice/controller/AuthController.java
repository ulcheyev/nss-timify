package com.kyki.usermicroservice.controller;

import com.kyki.usermicroservice.dto.AuthRequest;
import com.kyki.usermicroservice.dto.AuthResponse;
import com.kyki.usermicroservice.service.AuthService;
import com.kyki.usermicroservice.utils.TimifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping(path = "api/v1/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @NonNull AuthRequest request) {
        final HttpHeaders headers = TimifyUtils.createLocationHeaderFromCurrentUri("/api/home");
        log.info("AuthController-authenticate: " + request);
        return ResponseEntity.ok().headers(headers).body(authenticationService.authenticate(request));
    }


}
