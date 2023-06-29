package com.kyki.usermicroservice.service;

import com.kyki.usermicroservice.dto.RegistrationRequest;
import com.kyki.usermicroservice.model.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AppUserService {

    AppUser findById(Long appUserId);

    List<AppUser> findAll();

    AppUser findByUsername(String username);

    AppUser findByEmail(String email);

    AppUser save(RegistrationRequest request);

    void save(AppUser appUser);


    void update(Long appUserId, String username, String email);

    void deleteById(Long appUserId);

    Boolean isUser(Long userId);

    Boolean isAdmin(Long userId);

    Boolean isExists(Long userId);

    UserDetails loadUserByUsername(String username);
}
