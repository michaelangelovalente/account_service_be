package com.beije.account_service_be.business.authentication.repository;

import com.beije.account_service_be.business.authentication.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
