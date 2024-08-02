package com.beije.account_service_be.business.authentication.repository;

import com.beije.account_service_be.business.authentication.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<UserEntity> findByEmailCaseInsensitive(String email);
}
