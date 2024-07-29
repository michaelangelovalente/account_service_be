package com.beije.account_service_be.business.authentication.service;

import com.beije.account_service_be.business.authentication.domain.dto.request.UserRequestDto;
import com.beije.account_service_be.business.authentication.domain.dto.response.UserResponseDto;
import com.beije.account_service_be.business.authentication.domain.entity.UserEntity;
import com.beije.account_service_be.business.authentication.repository.UserRepository;
import com.beije.account_service_be.business.authentication.utils.AuthenticationMapper;
import com.beije.account_service_be.business.system_global.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationMapper authenticationMapper;

    private final UserRepository userRespository;

    public BaseDto signup(UserRequestDto userRequestDto) {
        log.info("User registration request: {}", userRequestDto);
        UserEntity userEntity = userRespository.save(
                authenticationMapper.userRequestDtoToUserEntity(userRequestDto)
        );

        UserResponseDto userResponseDto = authenticationMapper.userEntityToUserResponseDto(userEntity);
        return  userResponseDto;
    }
}
