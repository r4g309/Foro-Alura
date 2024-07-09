package dev.r4g309.foroalura.controller;

import dev.r4g309.foroalura.domain.topic.TopicRepository;
import dev.r4g309.foroalura.domain.topic.models.Topic;
import dev.r4g309.foroalura.domain.topic.models.dto.NewTopic;
import dev.r4g309.foroalura.domain.topic.models.dto.ResponseTopic;
import dev.r4g309.foroalura.domain.topic.models.dto.UpdateTopic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    private TopicRepository repository;

    @GetMapping
    @Operation(summary = "Get all topics")
    public ResponseEntity<Page<ResponseTopic>> getTopic(Pageable pageable) {
        var page = repository.findAll(pageable)
                .map(ResponseTopic::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Operation(summary = "Create a new topic")
    @Transactional
    public ResponseEntity<ResponseTopic> createTopic(@RequestBody @Valid NewTopic newTopic, UriComponentsBuilder uriBuilder) {
        Topic topic = new Topic(newTopic);
        repository.save(topic);
        URI uri = uriBuilder.path("/topic/{id}")
                .buildAndExpand(topic.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new ResponseTopic(topic));
    }

    @PutMapping
    @Operation(summary = "Update a topic")
    @Transactional
    public ResponseEntity<ResponseTopic> updateTopic(@RequestBody @Valid UpdateTopic updateTopic) {
        var topic = repository.findById(updateTopic.id())
                .orElseThrow();
        topic.setTitle(updateTopic.title());
        topic.setMessage(updateTopic.message());
        topic.setStatus(updateTopic.status());
        repository.save(topic);
        return ResponseEntity.ok(new ResponseTopic(topic));

    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete a topic")
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        var topic = repository.findById(id)
                .orElseThrow();
        repository.delete(topic);
        return ResponseEntity.ok()
                .build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a topic by id")
    public ResponseEntity<ResponseTopic> getTopicById(@PathVariable Long id) {
        var topic = repository.findById(id)
                .orElseThrow();
        return ResponseEntity.ok(new ResponseTopic(topic));
    }
}
