package com.beije.account_service_be.system_global.domain.dto.response;

import com.beije.account_service_be.system_global.domain.dto.BaseDto;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record InvalidResponseDto(
  String timestamp,
  Long status,
  String error,
  String path
) implements BaseDto {
}
