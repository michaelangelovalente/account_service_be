package com.beije.account_service_be.business.authentication.domain.dto.response;

import com.beije.account_service_be.system_global.domain.dto.BaseDto;
import lombok.Builder;

@Builder
public record UserResponseDto(
        Long id,
        String name,
        String lastname,
        String email
) implements BaseDto {
}
