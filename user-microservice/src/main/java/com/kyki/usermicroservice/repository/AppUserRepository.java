package com.kyki.usermicroservice.repository;

import com.kyki.usermicroservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findById(@NonNull Long aLong);

    Optional<AppUser> findAppUserByUsername(@NonNull String username);

    Optional<AppUser> findAppUserByEmail(@NonNull String email);

}
