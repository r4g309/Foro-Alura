package dev.r4g309.foroalura.domain.answer.model.dto;

import dev.r4g309.foroalura.domain.answer.model.Answer;

import java.time.LocalDateTime;

public record ResponseAnswer(

        Long id,
        String message,
        LocalDateTime creationDate,
        Long topicId
) {
    public ResponseAnswer(Answer answer) {
        this(answer.getId(), answer.getMessage(), answer.getCreationDate(), answer.getTopic()
                .getId());
    }
}
