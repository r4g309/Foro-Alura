package dev.r4g309.foroalura.domain.topic.models.dto;

import dev.r4g309.foroalura.domain.topic.models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopic(
        @NotNull Long id,
        @NotBlank String title,
        @NotBlank String message,
        @NotNull Status status

) {
}
