package dev.r4g309.foroalura.domain.answer.model;

import dev.r4g309.foroalura.domain.answer.model.dto.NewAnswer;
import dev.r4g309.foroalura.domain.topic.models.Topic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Answer(NewAnswer newAnswer) {
        this.message = newAnswer.message();
    }
}