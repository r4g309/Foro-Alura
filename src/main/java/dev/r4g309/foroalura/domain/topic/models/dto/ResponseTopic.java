package dev.r4g309.foroalura.domain.topic.models.dto;

import dev.r4g309.foroalura.domain.topic.models.Status;
import dev.r4g309.foroalura.domain.topic.models.Topic;

import java.time.LocalDateTime;

/**
 * DTO for {@link Topic}
 */
public record ResponseTopic(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        Status status) {

    public ResponseTopic(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus());
    }
}