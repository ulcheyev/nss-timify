package com.kyki.usermicroservice.service;

import com.kyki.usermicroservice.dto.AuthResponse;
import com.kyki.usermicroservice.dto.RegistrationRequest;
import com.kyki.usermicroservice.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

    private final AppUserService service;

    private final JwtUtils jwtUtils;

    public AuthResponse register(@RequestBody @NonNull RegistrationRequest request) {
        log.info("RegistrationService-register: " + request);
        service.save(request);
        final UserDetails userDetails = service.loadUserByUsername(request.getUsername());
        var jwtToken = jwtUtils.generateToken(userDetails);
        return AuthResponse
                .builder()
                .token(jwtToken)
                .build();
    }

}
