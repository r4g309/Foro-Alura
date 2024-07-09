package dev.r4g309.foroalura.domain.user.models.dto;

import dev.r4g309.foroalura.domain.user.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for {@link User}
 */
public record NewUser(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String password
) {
}