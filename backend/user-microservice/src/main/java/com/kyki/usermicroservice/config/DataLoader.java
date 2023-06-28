package com.kyki.usermicroservice.config;

import com.kyki.usermicroservice.exception.NotFoundException;
import com.kyki.usermicroservice.model.AppUser;
import com.kyki.usermicroservice.model.Role;
import com.kyki.usermicroservice.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private static final String username = "admin";
    private static final String pass = "admin";

    private final AppUserService service;

    @Override
    public void run(String... args) {
        loadAdmin();
    }

    private void loadAdmin() {
        try {
            service.findByUsername(username);
        } catch (NotFoundException e) {
            AppUser admin = AppUser
                    .builder()
                    .email("@admin@")
                    .password("admin")
                    .username("admin")
                    .role(Role.ADMIN)
                    .build();
            service.save(admin);
        }
    }


}
