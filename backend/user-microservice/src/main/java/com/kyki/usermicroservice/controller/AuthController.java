package com.kyki.usermicroservice.controller;

import com.kyki.usermicroservice.dto.AuthRequest;
import com.kyki.usermicroservice.dto.AuthResponse;
import com.kyki.usermicroservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "api/v1/system/login")
@CrossOrigin
public class AuthController {
    private HttpServletRequest request2;
    private final AuthService authenticationService;

    @Autowired
    public AuthController(HttpServletRequest request, AuthService authenticationService) {
        this.request2 = request;
        this.authenticationService = authenticationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @NonNull AuthRequest request) {
        String remoteAddr = request2.getRemoteAddr();
        log.info("AuthController-authenticate: " + request);
        log.info("Ip: " + remoteAddr);
        return ResponseEntity.ok().body(authenticationService.authenticate(request));
    }
}
