package dev.r4g309.foroalura.controller;

import dev.r4g309.foroalura.domain.answer.AnswerRepository;
import dev.r4g309.foroalura.domain.answer.model.Answer;
import dev.r4g309.foroalura.domain.answer.model.dto.NewAnswer;
import dev.r4g309.foroalura.domain.answer.model.dto.ResponseAnswer;
import dev.r4g309.foroalura.domain.answer.model.dto.UpdateAnswerer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/answer")
@Tag(name = "Answers")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {
    @Autowired
    private AnswerRepository repository;

    @GetMapping
    @Operation(summary = "Get all answers")
    public ResponseEntity<Page<ResponseAnswer>> getAnswer(Pageable pageable) {
        Page<ResponseAnswer> page = repository.findAll(pageable)
                .map(ResponseAnswer::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Operation(summary = "Create a new answer")
    @Transactional
    public ResponseEntity<ResponseAnswer> newAnswer(@RequestBody @Valid NewAnswer newAnswer, UriComponentsBuilder uriBuilder) {
        Answer answer = repository.save(new Answer(newAnswer));
        URI uri = uriBuilder.path("/answer/{id}")
                .buildAndExpand(answer.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(new ResponseAnswer(answer));
    }

    @PutMapping
    @Operation(summary = "Update an answer")
    @Transactional
    public ResponseEntity<ResponseAnswer> updateAnswer(@RequestBody @Valid UpdateAnswerer updateAnswerer) {
        Answer answer = repository.findById(updateAnswerer.id())
                .orElseThrow();
        answer.setMessage(updateAnswerer.message());
        repository.save(answer);
        return ResponseEntity.ok(new ResponseAnswer(answer));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an answer")
    @Transactional
    public ResponseEntity<ResponseAnswer> deleteAnswer(@PathVariable Long id) {
        Answer answer = repository.findById(id)
                .orElseThrow();
        repository.delete(answer);
        return ResponseEntity.ok(new ResponseAnswer(answer));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an answer by id")
    public ResponseEntity<ResponseAnswer> getAnswerById(@PathVariable Long id) {
        Answer answer = repository.findById(id)
                .orElseThrow();
        return ResponseEntity.ok(new ResponseAnswer(answer));
    }

}


