package dev.r4g309.foroalura.domain.answer;

import dev.r4g309.foroalura.domain.answer.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
