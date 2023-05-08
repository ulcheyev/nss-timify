package com.kyki.usermicroservice.service;

import com.kyki.usermicroservice.model.Role;
import com.kyki.usermicroservice.security.CustomUserDetails;
import com.kyki.usermicroservice.utils.Mapper;
import com.kyki.usermicroservice.dto.RegistrationRequest;
import com.kyki.usermicroservice.exception.NotFoundException;
import com.kyki.usermicroservice.exception.ValidationException;
import com.kyki.usermicroservice.model.AppUser;
import com.kyki.usermicroservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
     public AppUser findById(@NonNull Long appUserId) {
        log.info("AppUserService-findById: " + appUserId);
        return appUserRepository
                .findById(appUserId)
                .orElseThrow(() -> new NotFoundException("User with id " + appUserId + " does not exist."));
    }

    @Override
    public List<AppUser> findAll() {
        log.info("AppUserService-findAll");
        List<AppUser> all = appUserRepository.findAll();
        if(all.isEmpty()) {
            throw new NotFoundException("Table with users is empty.");
        }
        return all;
    }

    @Override
    public AppUser findByUsername(@NonNull String username) {
        log.info("AppUserService-findByUsername: " + username);
        return appUserRepository
                .findAppUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username " + username + " does not exist."));
    }

    @Override
    public AppUser findByEmail(@NonNull String email) {
        log.info("AppUserService-findByEmail: " + email);
        return appUserRepository
                .findAppUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " does not exist."));
    }

    @Override
    public void save(RegistrationRequest request) {
        log.info("AppUserService-save: " + request);
        Optional<AppUser> appUserByUsername = appUserRepository.findAppUserByUsername(request.getUsername());
        if(appUserByUsername.isPresent()) {
            throw new ValidationException("User with username " + request.getUsername() + " already exists.");
        }
        Optional<AppUser> appUserByEmail = appUserRepository.findAppUserByEmail(request.getEmail());
        if(appUserByEmail.isPresent()) {
            throw new ValidationException("User with email " + request.getEmail() + " already exists.");
        }

        AppUser entity = Mapper.toAppUser(request);
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        appUserRepository.save(entity);
    }

    @Transactional
    @Override
    public void update(@NonNull Long appUserId, String username, String email) {
        log.info("AppUserService-update: {}, {}, {} ", appUserId, username, email);
        AppUser appUser = findById(appUserId);

        if (username != null && username.length() > 0 && !appUser.getUsername().equals(username)) {
            Optional<AppUser> appUserByUsername = appUserRepository
                    .findAppUserByUsername(username);
            if (appUserByUsername.isPresent()) {
                throw new ValidationException("Username " + username + " has been taken.");
            }
            appUser.setUsername(username);
        }

        if (email != null && email.length() > 0 && !appUser.getEmail().equals(email)) {
            Optional<AppUser> appUserByEmail = appUserRepository
                    .findAppUserByEmail(email);
            if (appUserByEmail.isPresent()) {
                throw new ValidationException("Email " + email + " has been taken.");
            }
            appUser.setEmail(email);
        }
    }

    @Override
    public void deleteById(@NonNull Long appUserId) {
        log.info("AppUserService-deleteById: {}", appUserId);
        findById(appUserId);
        appUserRepository.deleteById(appUserId);
    }

    @Override
    public Boolean isUser(@NonNull Long userId) {
        log.info("AppUserService-isUser: {}", userId);
        return findById(userId).getRole().equals(Role.USER);
    }

    @Override
    public Boolean isAdmin(@NonNull Long userId) {
        log.info("AppUserService-isAdmin: {}", userId);
        return findById(userId).getRole().equals(Role.ADMIN);
    }

    @Override
    public Boolean isExists(@NonNull Long userId) {
        log.info("AppUserService-isExists: {}", userId);
        return findById(userId) != null;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        log.info("AppUserService-loadUserByUsername: {}", username);
        AppUser appUser = findByUsername(username);
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorityCollection = new ArrayList<>();
        simpleGrantedAuthorityCollection.add(new SimpleGrantedAuthority(appUser.getRole().toString()));
        CustomUserDetails details = new CustomUserDetails();
        details.setUser(new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), simpleGrantedAuthorityCollection));
        return details;
    }


}
