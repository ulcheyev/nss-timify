package com.kyki.usermicroservice.controller;

import com.kyki.usermicroservice.dto.AppUserUpdateRequest;
import com.kyki.usermicroservice.model.AppUser;
import com.kyki.usermicroservice.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/system/users")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080",
                        "http://34.125.160.101"})
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    //    @PreAuthorize("hasAnyRole('ADMIN')"
    public ResponseEntity<List<AppUser>> findAll() {
        log.info("AppUserController-findAll");
        List<AppUser> all = appUserService.findAll();
        return ResponseEntity.ok().body(all);
    }


    @GetMapping(value = "/filter")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<AppUser> findByUsername(@NonNull @RequestParam(value="userName")  String userName) {
        log.info("AppUserController-findByUsername: " + userName);
        AppUser byUsername = appUserService.findByUsername(userName);
        return ResponseEntity.ok().body(byUsername);
    }



    @DeleteMapping(value = "/{userId}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteUserById(@NonNull @PathVariable("userId") Long userId) {
        log.info("AppUserController-deleteUserById: " + userId);
        appUserService.deleteById(userId);
        return ResponseEntity.ok().body("User Successfully deleted");
    }

    @PutMapping(value = "/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAnyRole('USER') and @appUserServiceImpl.findByUsername(#userName).username.equals(principal.username) or hasRole('ADMIN')")
    public ResponseEntity<String> updateUser(@NonNull @PathVariable("userName") String userName,
                                             @RequestBody @NonNull AppUserUpdateRequest request) {
        log.info("AppUserController-updateUser: {}, {}" + userName, request);
        appUserService.update(appUserService.findByUsername(userName).getId(), request.getUsername(), request.getEmail());
        return ResponseEntity.ok().body("User Successfully updated");
    }

    @GetMapping(value = "/is-user")
    public Boolean isUser(@NonNull @RequestParam(value="userName") String name) {
        return appUserService.isUser(appUserService.findByUsername(name).getId());
    }

    @GetMapping(value = "/is-admin")
    public Boolean isAdmin(@NonNull @RequestParam(value="userName") String name) {
        return appUserService.isAdmin(appUserService.findByUsername(name).getId());

    }

    @GetMapping(value = "/is-exists")
    public Boolean isExists(@NonNull @RequestParam(value="userName") String name) {
        return appUserService.isExists(appUserService.findByUsername(name).getId());
    }

}
