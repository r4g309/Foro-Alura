package dev.r4g309.foroalura.domain.user.models.dto;

import dev.r4g309.foroalura.domain.user.models.User;


/**
 * DTO for {@link User}
 */
public record UserResponse(
        Long id,
        String name,
        String email
) {
    public UserResponse(User user) {
        this(user.getId(), user.getName(), user.getEmail());

    }
}