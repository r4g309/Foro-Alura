package dev.r4g309.foroalura.domain.topic.models.dto;

import jakarta.validation.constraints.NotBlank;

public record NewTopic(
        @NotBlank String title,
        @NotBlank String message
) {
}
