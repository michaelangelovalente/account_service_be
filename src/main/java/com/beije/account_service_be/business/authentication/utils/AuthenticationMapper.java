package com.beije.account_service_be.business.authentication.utils;

import com.beije.account_service_be.business.authentication.domain.dto.request.UserRequestDto;
import com.beije.account_service_be.business.authentication.domain.dto.response.UserResponseDto;
import com.beije.account_service_be.business.authentication.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {


    public UserEntity userRequestDtoToUserEntity(UserRequestDto userRequestDto) {
        return UserEntity.builder()
                .name(userRequestDto.name())
                .lastname(userRequestDto.lastname())
                .email(userRequestDto.email())
                .password(userRequestDto.password())
                .build();
    }

    public UserResponseDto userEntityToUserResponseDto(UserEntity userEntity) {
        return UserResponseDto.builder()
                .name(userEntity.getName())
                .lastname(userEntity.getLastname())
                .email(userEntity.getEmail())
                .build();
    }
}
