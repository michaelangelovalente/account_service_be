package com.beije.account_service_be.business.authentication.domain.dto.response;

import com.beije.account_service_be.business.system_global.domain.dto.BaseDto;
import lombok.Builder;

@Builder
public record UserResponseDto(
        String name,
        String lastname,
        String email
) implements BaseDto {
}
