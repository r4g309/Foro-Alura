package dev.r4g309.foroalura.domain.answer.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewAnswer(
        @NotBlank String message,
        @NotNull Long topicId
) {
}
