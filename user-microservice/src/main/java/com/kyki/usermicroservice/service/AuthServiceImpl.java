package com.kyki.usermicroservice.service;

import com.kyki.usermicroservice.dto.AuthRequest;
import com.kyki.usermicroservice.dto.AuthResponse;
import com.kyki.usermicroservice.exception.ValidationException;
import com.kyki.usermicroservice.security.CustomUserDetails;
import com.kyki.usermicroservice.security.JwtUtils;
import com.kyki.usermicroservice.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AppUserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(@RequestBody @NonNull AuthRequest request) {

        log.info("AuthService-authenticate: " + request);

        final CustomUserDetails userDetails = (CustomUserDetails) service.loadUserByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new ValidationException("Invalid password or username");
        }
        log.info("AuthService-authenticate: match pass");

        Authentication authentication = authenticationManager
                .authenticate(SecurityUtils.generateUserPassToken(request, userDetails));

        SecurityUtils.setCurrentUser(authentication);
        var jwtToken = jwtUtils.generateToken(userDetails);
        log.info("AuthService-authenticate: token generated");

        userDetails.erasePass();

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }


}
