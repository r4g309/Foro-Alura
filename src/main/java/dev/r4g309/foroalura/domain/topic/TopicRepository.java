package dev.r4g309.foroalura.domain.topic;

import dev.r4g309.foroalura.domain.topic.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
