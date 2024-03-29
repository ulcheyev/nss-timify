package com.kyki.usermicroservice.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kyki.usermicroservice.dto.AppUserUpdateRequest;
import com.kyki.usermicroservice.dto.IsExistsResponse;
import com.kyki.usermicroservice.model.AppUser;
import com.kyki.usermicroservice.security.JwtUtils;
import com.kyki.usermicroservice.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/system/users")
public class AppUserController {

    private final AppUserService appUserService;
    private final JwtUtils jwtUtils;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AppUser>> findAll() {
        log.info("AppUserController-findAll");
        List<AppUser> all = appUserService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping(value = "/filter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AppUser> findByUsername(@NonNull @RequestParam(value="userName")  String userName) {
        log.info("AppUserController-findByUsername: " + userName);
        AppUser byUsername = appUserService.findByUsername(userName);
        return ResponseEntity.ok().body(byUsername);
    }



    @DeleteMapping(value = "/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUserById(@NonNull @PathVariable("userId") Long userId) {
        log.info("AppUserController-deleteUserById: " + userId);
        appUserService.deleteById(userId);
        return ResponseEntity.ok().body("User Successfully deleted");
    }

    @PutMapping(value = "/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER') and @appUserServiceImpl.findByUsername(#userName).username.equals(principal.username) or hasRole('ADMIN')")
    public ResponseEntity<String> updateUser(@NonNull @PathVariable("userName") String userName,
                                             @RequestBody @NonNull AppUserUpdateRequest request) {
        log.info("AppUserController-updateUser: {}, {}" + userName, request);
        appUserService.update(appUserService.findByUsername(userName).getId(), request.getUsername(), request.getEmail());
        return ResponseEntity.ok().body("User Successfully updated");
    }

    @GetMapping(value = "/is-user")
    public  ResponseEntity<IsExistsResponse> isUser(@NonNull @RequestParam(value="tok") String token) {

        return ResponseEntity.ok().body(IsExistsResponse.builder().ex(appUserService
                .isUser(appUserService.findByUsername(jwtUtils.extractUsername(token)).getId()))
                .build());
    }

    @GetMapping(value = "/is-admin")
    public  ResponseEntity<IsExistsResponse> isAdmin(@NonNull @RequestParam(value="tok") String token) {

        return ResponseEntity.ok().body(IsExistsResponse.builder().ex(appUserService
                .isAdmin(appUserService.findByUsername(jwtUtils.extractUsername(token)).getId()))
                .build()
        );

    }

    @GetMapping(value = "/is-exists")
    public ResponseEntity<IsExistsResponse> isExists(@NonNull @RequestParam(value="userName") String name) {
        return ResponseEntity.ok().body(IsExistsResponse.builder().ex(appUserService
                .isExists(appUserService.findByUsername(name).getId())).build());
    }

}
