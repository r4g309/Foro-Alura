package dev.r4g309.foroalura.domain.topic.models;

import dev.r4g309.foroalura.domain.answer.model.Answer;
import dev.r4g309.foroalura.domain.topic.models.dto.NewTopic;
import dev.r4g309.foroalura.domain.user.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Topic")
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Topic(NewTopic newTopic) {
        this.title = newTopic.title();
        this.message = newTopic.message();
        this.creationDate = LocalDateTime.now();
        this.status = Status.NOT_ANSWERED;
    }
}