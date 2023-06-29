package com.kyki.usermicroservice.service;

import com.kyki.usermicroservice.dto.AuthResponse;
import com.kyki.usermicroservice.dto.RegResponse;
import com.kyki.usermicroservice.dto.RegistrationRequest;
import com.kyki.usermicroservice.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

    private final AppUserService service;

    private final JwtUtils jwtUtils;

    public RegResponse register(@RequestBody @NonNull RegistrationRequest request) {

        log.info("RegistrationService-register: " + request);
        var saved = service.save(request);
        final UserDetails userDetails = service.loadUserByUsername(request.getUsername());
        var jwtToken = jwtUtils.generateToken(userDetails);
        return RegResponse
                .builder()
                .token(jwtToken)
                .id(saved.getId())
                .build();
    }

}
