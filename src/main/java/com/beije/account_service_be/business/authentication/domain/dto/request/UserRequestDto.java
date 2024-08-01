package com.beije.account_service_be.business.authentication.domain.dto.request;

import com.beije.account_service_be.system_global.domain.dto.BaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
public record UserRequestDto(
        @NotBlank(message="Required field: name")
        String name,

        @NotBlank(message="Required field: lastname")
        String lastname,

        @NotBlank(message="Required field: email")
        @Email
        @Pattern(regexp = "^[\\w._%+-]+@beije\\.com$", message = "Email must end with @beije.com")
        String email,

        @NotBlank(message="Required field: password")

        String password
) implements BaseDto {
}
