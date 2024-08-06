package com.beije.account_service_be.system_global.domain.dto.response;

import lombok.Builder;

@Builder
public record GenericErrorResponseDto(
        String timestamp,
        Long status,
        String error,
        String message,
        String path
) {
}
